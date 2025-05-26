package ru.skypro.homework.dto;

import lombok.Data;

import jakarta.validation.constraints.Size;

// Обновление пароля пользователя
@Data
public class NewPassword {
    @Size(min = 8, max = 16)
    private String currentPassword;

    @Size(min = 8, max = 16)
    private String newPassword;
}
