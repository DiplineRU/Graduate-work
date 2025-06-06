package ru.skypro.homework.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;


// Создание или обновление объявления
@Data
public class CreateOrUpdateAd {
    @Size(min = 4, max = 32)
    private String title;

    @Min(0)
    @Max(10_000_000)
    private Integer price;

    @Size(min = 8, max = 64)
    private String description;
    private MultipartFile imageFile;
}