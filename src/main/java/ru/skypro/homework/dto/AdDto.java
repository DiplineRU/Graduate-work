package ru.skypro.homework.dto;

import lombok.Data;

// Основная информация об объявлении
@Data
public class AdDto {

    private Integer author;  // ID автора
    private String image;    // Ссылка на картинку
    private Integer id;      // ID объявления
    private Integer price;   // Цена
    private String title;    // Заголовок
    private String authorFirstName; // Имя автора
    private String authorLastName;  // Фамилия автора
    private String phone;           // Телефон автора
}
