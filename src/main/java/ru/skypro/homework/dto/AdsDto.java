package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

// Список объявлений с пагинацией
@Data
public class AdsDto {
    private Integer count;       // Общее количество объявлений
    private List<AdDto> results;    // Список объявлений

    public AdsDto(Integer count, List<AdDto> results) {
        this.count = count;
        this.results = results;
    }

}
