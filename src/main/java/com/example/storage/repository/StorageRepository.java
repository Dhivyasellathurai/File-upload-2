package com.example.storage.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.storage.entity.ImageData;

public interface StorageRepository extends JpaRepository<ImageData, Long> {
	
	Optional<ImageData> findByName(String fileName);

}