package ru.skypro.homework.dto;

import lombok.Data;

// Комментарий к объявлению
@Data
public class CommentDto {
    private Integer author;         // ID автора
    private String authorImage;     // Ссылка на аватар
    private String authorFirstName; // Имя автора
    private Long createdAt;         // Дата создания (timestamp)
    private Integer pk;             // ID комментария
    private String text;            // Текст комментария

}
