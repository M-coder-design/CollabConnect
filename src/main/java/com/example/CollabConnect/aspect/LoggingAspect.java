package com.example.CollabConnect.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.CollabConnect.service.*.*(..))")
    public void LogBeforeMethodExecution(JoinPoint joinPoint){
        System.out.println("Executing method: " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.example.CollabConnect.service.*.*(..))")
    public void LogAfterMethodExecution(JoinPoint joinPoint)
    {
        System.out.println("Method execution: " + joinPoint.getSignature().getName());
    }

    
}
