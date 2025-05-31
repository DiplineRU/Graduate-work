package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.model.Comment;

import java.time.Instant;

@Mapper(componentModel = "spring", imports = Instant.class)
public interface CommentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(Instant.now())")
    @Mapping(target = "text", source = "text")
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "ad", ignore = true)
    @Mapping(target = "authorImage", ignore = true)
    @Mapping(target = "authorFirstName", ignore = true)
    Comment toEntity(CommentDto dto);

    @Mapping(target = "author", source = "user.id")
    @Mapping(target = "authorImage", source = "user.image")
    @Mapping(target = "authorFirstName", source = "user.firstName")
    @Mapping(target = "createdAt", expression = "java(comment.getCreatedAt().toEpochMilli())")
    @Mapping(target = "text", source = "text")
    CommentDto toDto(Comment comment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "ad", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "authorImage", ignore = true)
    @Mapping(target = "authorFirstName", ignore = true)
    void updateFromDto(CommentDto dto, @MappingTarget Comment entity);

}
