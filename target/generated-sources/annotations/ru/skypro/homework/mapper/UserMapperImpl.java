package ru.skypro.homework.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-30T12:49:12+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.15 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User registerToUser(Register register) {
        if ( register == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.username( register.getUsername() );
        user.password( register.getPassword() );
        user.firstName( register.getFirstName() );
        user.lastName( register.getLastName() );
        user.phone( register.getPhone() );
        user.email( register.getEmail() );

        user.role( register.getRole() != null ? register.getRole() : ru.skypro.homework.model.Role.USER );

        return user.build();
    }

    @Override
    public UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setEmail( user.getEmail() );
        userDto.setFirstName( user.getFirstName() );
        userDto.setLastName( user.getLastName() );
        userDto.setPhone( user.getPhone() );
        userDto.setRole( user.getRole() );
        userDto.setImage( user.getImage() );

        return userDto;
    }

    @Override
    public void updateUserFromDto(UserDto userDto, User user) {
        if ( userDto == null ) {
            return;
        }

        user.setFirstName( userDto.getFirstName() );
        user.setLastName( userDto.getLastName() );
        user.setPhone( userDto.getPhone() );
        user.setEmail( userDto.getEmail() );
    }
}
