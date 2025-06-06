package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    String saveImage(MultipartFile image) throws IOException;
    byte[] getImage(String imageName) throws IOException;
    void deleteImage(String imageName) throws IOException;

}
