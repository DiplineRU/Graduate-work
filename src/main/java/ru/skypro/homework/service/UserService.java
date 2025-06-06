package ru.skypro.homework.service;

import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.User;

import java.util.List;

public interface UserService {

    UserDto registerUser(Register register);

    UserDto updateUser(Long userId, UserDto userDto);

    void deleteUser(Long userId);

    List<UserDto> getAllUsers();

    UserDto getUserById(Long userId);
    User getUser(String username);

    User findByAllUser();

}
