package ru.skypro.homework.service;

import ru.skypro.homework.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto registerUser(UserDto userDto);
    UserDto updateUser(Long userId, UserDto userDto);
    void deleteUser(Long userId);
    List<UserDto> getAllUsers();
    UserDto getUserById(Long userId);
}
