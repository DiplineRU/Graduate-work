package ru.skypro.homework.dto;

import lombok.Data;

import jakarta.validation.constraints.Size;

// Авторизация пользователя
@Data
public class Login {
    @Size(min = 4, max = 32)
    private String username;
    @Size(min = 8, max = 32)
    private String password;
}
