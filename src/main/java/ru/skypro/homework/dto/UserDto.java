package ru.skypro.homework.dto;

import lombok.Data;

// Информация о пользователе
@Data
public class UserDto {
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;  // USER или ADMIN
    private String image; // Ссылка на аватар

}
