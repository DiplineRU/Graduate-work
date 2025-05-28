package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CommentService;

import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentDto addComment(Long adId, CommentDto commentDto, Long userId) {
        return null;
    }

    @Override
    public void deleteComment(Long commentId) {

    }

    @Override
    public List<CommentDto> getCommentsByAdId(Long adId) {
        return null;
    }
}
