package ru.skypro.homework.dto;

import lombok.Data;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

// Создание или обновление объявления
@Data
public class CreateOrUpdateAd {
    @Size (min = 4, max = 32)
    private String title;

    @Min(0)
    @Max(10_000_000)
    private Integer price;

    @Size(min = 8, max = 64)
    private String description;

}
