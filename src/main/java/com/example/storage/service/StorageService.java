package com.example.storage.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.storage.entity.ImageData;
import com.example.storage.repository.StorageRepository;
import com.example.storage.util.ImageUtils;


@Service
public class StorageService {

	   @Autowired
	    private StorageRepository repository;

	    public String uploadImage(MultipartFile file) throws IOException {

	        ImageData imageData = repository.save(ImageData.builder()
	                .name(file.getOriginalFilename())
	                .type(file.getContentType())
	                .imageData(ImageUtils.compressImage(file.getBytes())).build());
	        if (imageData != null) {
	            return "file uploaded successfully : " + file.getOriginalFilename();
	        }
	        return null;
	    }

	    public byte[] downloadImage(String fileName){
	        Optional<ImageData> dbImageData = repository.findByName(fileName);
	        byte[] images=ImageUtils.decompressImage(dbImageData.get().getImageData());
	        return images;
	    }
}
 