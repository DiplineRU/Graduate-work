package ru.skypro.homework.dto;

import lombok.Data;

import jakarta.validation.constraints.Size;

// Создание или обновление комментария
@Data
public class CreateOrUpdateComment {

    @Size(min = 8, max = 64)
    private String text;
}
