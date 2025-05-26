package ru.skypro.homework.dto;

import lombok.Data;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

// Обновление данных пользователя.
@Data
public class UpdateUser {
    @Size(min = 3, max = 10)
    private String firstName;

    @Size(min = 3, max = 10)
    private String lastName;

    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;

}
