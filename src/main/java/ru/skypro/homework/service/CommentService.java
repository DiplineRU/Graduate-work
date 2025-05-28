package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto addComment(Long adId, CommentDto commentDto, Long userId);
    void deleteComment(Long commentId);
    List<CommentDto> getCommentsByAdId(Long adId);

}
