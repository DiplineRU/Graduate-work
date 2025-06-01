package ru.skypro.homework.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.UserEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-01T23:26:23+0400",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.14 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity toUpdateUser(UpdateUserDto source) {
        if ( source == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.firstName( source.getFirstName() );
        userEntity.lastName( source.getLastName() );
        userEntity.phone( source.getPhone() );

        return userEntity.build();
    }

    @Override
    public UserEntity toUser(RegisterDto source) {
        if ( source == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.email( source.getUsername() );
        userEntity.firstName( source.getFirstName() );
        userEntity.lastName( source.getLastName() );
        userEntity.password( source.getPassword() );
        userEntity.phone( source.getPhone() );
        userEntity.role( source.getRole() );

        return userEntity.build();
    }

    @Override
    public UserDto toUserDto(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setRole( userEntity.getRole() );
        if ( userEntity.getId() != null ) {
            userDto.setId( userEntity.getId() );
        }
        userDto.setEmail( userEntity.getEmail() );
        userDto.setFirstName( userEntity.getFirstName() );
        userDto.setLastName( userEntity.getLastName() );
        userDto.setPhone( userEntity.getPhone() );

        userDto.setImage( buildImageUrl(userEntity.getAvatar().getId(), userEntity.getAvatar()) );

        return userDto;
    }
}
