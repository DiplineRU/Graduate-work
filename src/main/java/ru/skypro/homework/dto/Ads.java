package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

// Список объявлений с пагинацией
@Data
public class Ads {
    private Integer count;       // Общее количество объявлений
    private List<AdDTO> results;    // Список объявлений

}
