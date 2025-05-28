package ru.skypro.homework.mapper;

import org.mapstruct.MappingTarget;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper (componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    // Маппинг RegisterReq -> User (с установкой роли по умолчанию)
    @Mapping(target = "role", constant = "USER") // role = "USER" (если Role — это enum)
    @Mapping(target = "password", ignore = true)  // Пароль обрабатывается отдельно
    User toEntity(Register register);

    // Обновление User из UserDto (для PATCH-запросов)
    @Mapping(target = "id", ignore = true) // ID не обновляем
    void updateUserFromDto(UserDto userDto, @MappingTarget User user);
}
