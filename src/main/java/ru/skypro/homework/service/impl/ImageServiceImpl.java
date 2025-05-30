package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class ImageServiceImpl implements ImageService {
    @Value("${image.upload.directory}")
    private String uploadDirectory;

    @Override
    public String saveImage(MultipartFile image) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
        Path filePath = Paths.get(uploadDirectory, fileName);
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, image.getBytes());
        return fileName;
    }

    @Override
    public byte[] getImage(String imageName) throws IOException {
        Path filePath = Paths.get(uploadDirectory, imageName);
        return Files.readAllBytes(filePath);
    }

    @Override
    public void deleteImage(String imageName) throws IOException {
        Path filePath = Paths.get(uploadDirectory, imageName);
        Files.deleteIfExists(filePath);
    }
}
