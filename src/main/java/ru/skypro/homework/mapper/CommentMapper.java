package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.model.Comment;

import java.time.Instant;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "id", ignore = true) // Игнорируем, так как ID генерируется БД
    @Mapping(target = "authorImage", source = "dto.authorImage")
    @Mapping(target = "authorFirstName", source = "dto.authorFirstName")
    @Mapping(target = "createdAt", expression = "java(convertLongToInstant(dto.getCreatedAt()))")
    @Mapping(target = "text", source = "dto.text")
    @Mapping(target = "user", ignore = true) // Устанавливается отдельно в сервисе
    @Mapping(target = "ad", ignore = true) // Устанавливается отдельно в сервисе
    Comment toEntity(CommentDto dto);

    @Mapping(target = "author", expression = "java(comment.getUser() != null ? comment.getUser().getId() : null)")
    @Mapping(target = "authorImage", source = "comment.authorImage")
    @Mapping(target = "authorFirstName", source = "comment.authorFirstName")
    @Mapping(target = "createdAt", expression = "java(convertInstantToLong(comment.getCreatedAt()))")
    @Mapping(target = "id", source = "comment.id")
    @Mapping(target = "text", source = "comment.text")
    CommentDto toDto(Comment comment);

    default Instant convertLongToInstant(Long timestamp) {
        return timestamp != null ?
                Instant.ofEpochMilli(timestamp) :
                Instant.now();
    }

    default Long convertInstantToLong(Instant instant) {
        return instant != null ?
                instant.toEpochMilli() :
                null;
    }
    @Mapping(target = "authorImage", source = "dto.authorImage")
    @Mapping(target = "authorFirstName", source = "dto.authorFirstName")
    @Mapping(target = "createdAt", expression = "java(convertLongToInstant(dto.getCreatedAt()))")
    @Mapping(target = "text", source = "dto.text")
    @Mapping(target = "id", ignore = true)         // Игнорим ID
    @Mapping(target = "user", ignore = true)       // Игнорим пользователя
    @Mapping(target = "ad", ignore = true)         // Игнорим объявление
    void updateCommentFromDto(CommentDto dto, @MappingTarget Comment comment);
}
