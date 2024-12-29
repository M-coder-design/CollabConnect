package com.example.CollabConnect.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class VideoStorageService {

    @Value("${video.storage.location}")
    private String videoStorageLocation;

    public String saveVideo(MultipartFile videoFile) throws IOException {
        Path storagePath = Paths.get(videoStorageLocation);
        if (!Files.exists(storagePath)) {
            Files.createDirectories(storagePath); // Ensure the directory exist
        }

        String filePath = storagePath.resolve(videoFile.getOriginalFilename()).toString();
        videoFile.transferTo(new File(filePath)); // Save the video file
        return filePath;
    }
}
