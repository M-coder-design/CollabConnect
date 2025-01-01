package com.example.CollabConnect.service;

import com.example.CollabConnect.entity.postgres.VideoMetadata;
import com.example.CollabConnect.repository.postgres.VideoMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

@Service
public class VideoStorageService {

    @Value("${video.storage.location}")
    private String videoStorageLocation;

    @Autowired
    private VideoMetadataRepository metadataRepository;


    public String saveVideo(MultipartFile videoFile) throws IOException {
        Path storagePath = Paths.get(videoStorageLocation).toAbsolutePath().normalize();
        if (!Files.exists(storagePath)) {
            Files.createDirectories(storagePath);
        }

        String originalFileName = videoFile.getOriginalFilename();
        String uniqueFileName = System.currentTimeMillis() + "_" + originalFileName;
        Path filePath = storagePath.resolve(uniqueFileName);

        videoFile.transferTo(filePath.toFile());

        // Save metadata
        VideoMetadata metadata = new VideoMetadata(
                originalFileName,
                filePath.toString(),
                LocalDateTime.now(),
                videoFile.getSize()
        );
        metadataRepository.save(metadata);

        return filePath.toString();
    }
}
