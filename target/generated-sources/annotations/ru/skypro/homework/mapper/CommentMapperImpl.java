package ru.skypro.homework.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.model.Comment;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-30T12:49:12+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.15 (Amazon.com Inc.)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment toEntity(CommentDto dto) {
        if ( dto == null ) {
            return null;
        }

        Comment.CommentBuilder comment = Comment.builder();

        comment.authorImage( dto.getAuthorImage() );
        comment.authorFirstName( dto.getAuthorFirstName() );
        comment.text( dto.getText() );

        comment.createdAt( convertLongToInstant(dto.getCreatedAt()) );

        return comment.build();
    }

    @Override
    public CommentDto toDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentDto commentDto = new CommentDto();

        commentDto.setAuthorImage( comment.getAuthorImage() );
        commentDto.setAuthorFirstName( comment.getAuthorFirstName() );
        commentDto.setId( comment.getId() );
        commentDto.setText( comment.getText() );

        commentDto.setAuthor( comment.getUser() != null ? comment.getUser().getId() : null );
        commentDto.setCreatedAt( convertInstantToLong(comment.getCreatedAt()) );

        return commentDto;
    }

    @Override
    public void updateCommentFromDto(CommentDto dto, Comment comment) {
        if ( dto == null ) {
            return;
        }

        comment.setAuthorImage( dto.getAuthorImage() );
        comment.setAuthorFirstName( dto.getAuthorFirstName() );
        comment.setText( dto.getText() );

        comment.setCreatedAt( convertLongToInstant(dto.getCreatedAt()) );
    }
}
