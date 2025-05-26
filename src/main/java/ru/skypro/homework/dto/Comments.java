package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

//Список комментариев с пагинацией.
@Data
public class Comments {
    private Integer count;            // Общее количество комментариев
    private List<CommentDTO> results;    // Список комментариев

}
