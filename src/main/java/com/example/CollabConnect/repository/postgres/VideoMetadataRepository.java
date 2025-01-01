package com.example.CollabConnect.repository.postgres;

import com.example.CollabConnect.entity.postgres.VideoMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoMetadataRepository extends JpaRepository<VideoMetadata, Long> {

    VideoMetadata findByFilename(String filename);
}
