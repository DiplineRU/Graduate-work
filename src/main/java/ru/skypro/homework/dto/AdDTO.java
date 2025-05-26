package ru.skypro.homework.dto;

import lombok.Data;

// Основная информация об объявлении
@Data
public class AdDTO {

    private Integer author;  // ID автора
    private String image;    // Ссылка на картинку
    private Integer pk;      // ID объявления
    private Integer price;   // Цена
    private String title;    // Заголовок

}
