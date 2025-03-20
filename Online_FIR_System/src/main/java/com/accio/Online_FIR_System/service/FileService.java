package com.accio.Online_FIR_System.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    private final String uploadDir = "uploads/";

    public String uploadFile(MultipartFile file) {
        try {
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Get original file name and target file path
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);

            // Save the file to the upload directory
            Files.copy(file.getInputStream(), filePath);

            // Return the relative file path to store in the database
            return uploadDir + fileName;

        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }

    public byte[] getFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readAllBytes(path);
    }
}

