package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.entity.ImageAdEntity;

import java.io.IOException;

public interface ImageAdService {
    ImageAdEntity updateAdImage(Integer id, MultipartFile file) throws IOException;
    byte[] getImageAd(Integer id) throws IOException;
    AdDto createAd(AdDto AdDto, MultipartFile image, String userName) throws IOException;
}