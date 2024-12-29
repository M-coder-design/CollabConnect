package com.example.CollabConnect.config;

import com.example.CollabConnect.service.ChatSubscriber;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisConfig {

    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        return new LettuceConnectionFactory();
    }

//    @Bean
//    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory);
//
//        // Use StringRedisSerializer for keys
//        template.setKeySerializer(new StringRedisSerializer());
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//
//        // Use GenericJackson2JsonRedisSerializer for values
//        template.setValueSerializer(new GenericJackson2JsonRedisSerializer(objectMapper));
//        template.setHashKeySerializer(new StringRedisSerializer());
//        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer(objectMapper));
//
//        return template;
//    }
//

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        // Use StringRedisSerializer for keys
        template.setKeySerializer(new StringRedisSerializer());

        // Configure the value serializer with JavaTimeModule for LocalDateTime
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        template.setValueSerializer(new GenericJackson2JsonRedisSerializer(objectMapper));
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer(objectMapper));

        return template;
    }

    //    @Bean
//    public MessageListenerAdapter messageListenerAdapter(ChatSubscriber chatSubscriber)
//    {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//        MessageListenerAdapter adapter = new MessageListenerAdapter(chatSubscriber, "handleMessage");
//        adapter.setSerializer(new GenericJackson2JsonRedisSerializer(objectMapper));
//        return adapter;
//    }
    @Bean
    public MessageListenerAdapter messageListenerAdapter(ChatSubscriber chatSubscriber) {
        return new MessageListenerAdapter(chatSubscriber, "handleMessage");
    }


    @Bean
    public ChannelTopic topic(){
        return new ChannelTopic("chat");
    }

//    @Bean
//    public RedisMessageListenerContainer redisMessageListenerContainer(
//            RedisConnectionFactory redisConnectionFactory,
//            MessageListenerAdapter messageListenerAdapter,
//            ChannelTopic channelTopic
//    )
//    {
//        RedisMessageListenerContainer container =
//                new RedisMessageListenerContainer();
//
//        container.setConnectionFactory(redisConnectionFactory);
//
//        // Add the message listener and topic to the container
//        container.addMessageListener(messageListenerAdapter, channelTopic);
//        return container;
//    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(
            RedisConnectionFactory redisConnectionFactory,
            MessageListenerAdapter messageListenerAdapter,
            ChannelTopic channelTopic
    ) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        container.addMessageListener(messageListenerAdapter, channelTopic);
        return container;
    }
}
