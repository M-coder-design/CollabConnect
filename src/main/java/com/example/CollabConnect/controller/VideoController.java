package com.example.CollabConnect.controller;

import com.example.CollabConnect.service.VideoStorageService;
import com.example.CollabConnect.service.VideoStreamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRange;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/videos/")
public class VideoController {

    @Autowired
    private VideoStorageService videoStorageService;

    @Autowired
    private VideoStreamingService videoStreamingService;

    @PostMapping("/upload")
    private String uploadVideo(@RequestParam("file") MultipartFile videoFile) throws IOException
    {
        return videoStorageService.saveVideo(videoFile);
    }

    @GetMapping("/stream/{filename}")
    public ResponseEntity<?> streamVideo(
            @PathVariable String filename,
            @RequestHeader HttpHeaders httpHeaders
            ) throws IOException{
        HttpRange range = httpHeaders.getRange().isEmpty() ? HttpRange.createByteRange(0) : httpHeaders.getRange().get(0);
        return videoStreamingService.streamVideo(filename, range);
    }
}
