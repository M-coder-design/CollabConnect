package com.example.CollabConnect.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class VideoStreamingService {

    private final String videoStorageLocation = "E:/JAVA_ENTERPRISE/CollabConnect/src/main/java/com/example/CollabConnect/uploads/videos";

    public ResponseEntity<Resource> streamVideo(String filename, HttpRange range) throws IOException {
        Path videoPath = Paths.get(videoStorageLocation).resolve(filename).normalize();
        Resource resource = new UrlResource(videoPath.toUri());

        if (!resource.exists()) {
            throw new RuntimeException("Video not found: " + filename);
        }

        long fileLength = resource.contentLength();
        long start = range.getRangeStart(0);
        long end = range.getRangeEnd(fileLength - 1);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Range", "bytes " + start + "-" + end + "/" + fileLength);
        headers.add(HttpHeaders.ACCEPT_RANGES, "bytes");

        return ResponseEntity.status(206) // HTTP 206: Partial Content
                .headers(headers)
                .contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(resource);
    }
}

