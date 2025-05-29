package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CommentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final AdServiceImpl adService;
    private final UserServiceImpl userService;

    @Override
    public CommentDto addComment(Integer adId, CommentDto commentDto, String username) {
        Ad ad = adService.getAdEntity(adId);
        User user = userService.getUser(username);

        Comment comment = commentMapper.toEntity(commentDto);
        comment.setAd(ad);
        comment.setUser(user);

        Comment savedComment = commentRepository.save(comment);
        return commentMapper.toDto(savedComment);
    }

    @Override
    public List<CommentDto> getCommentsByAdId(Integer adId) {
        return commentRepository.findAllByAdId(adId).stream()
                .map(commentMapper::toDto)
                .toList();
    }

    @Override
    public CommentDto updateComment(Integer add, Integer commentId, CommentDto createCommentDto, String username) {
        Comment comment = getCommentEntity(commentId);
        validateCommentOwnership(comment, username);

        commentMapper.updateCommentFromDto(createCommentDto, comment);
        Comment updatedComment = commentRepository.save(comment);
        return commentMapper.toDto(updatedComment);
    }


    @Override
    public void deleteComment(Integer adId, Integer commentId, String username) {
        Comment comment = getCommentEntity(commentId);
        validateCommentOwnership(comment, username);
        commentRepository.delete(comment);
    }

    private Comment getCommentEntity(Integer commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Комментарий не найден"));
    }

    private void validateCommentOwnership(Comment comment, String username) {
        if (!comment.getUser().getEmail().equals(username)) {
            throw new RuntimeException("Вы можете изменять только свои собственные комментарии");
        }
    }
}
