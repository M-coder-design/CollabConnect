package com.example.CollabConnect.entity.postgres;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "video_metadata")
public class VideoMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String filename;

    @Column(nullable = false)
    private String filePath;

    @Column(nullable = false)
    private LocalDateTime uploadTime;

    @Column(nullable = false)
    private Long fileSize;

    public VideoMetadata(){}

    public VideoMetadata(String filename, String filePath, LocalDateTime uploadTime, Long fileSize) {
        this.filename = filename;
        this.filePath = filePath;
        this.uploadTime = uploadTime;
        this.fileSize = fileSize;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
}
