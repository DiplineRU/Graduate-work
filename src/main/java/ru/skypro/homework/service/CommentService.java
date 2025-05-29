package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentDto;

import java.util.List;

public interface CommentService {


    CommentDto addComment(Integer adId, CommentDto commentDto, String username);

    List<CommentDto> getCommentsByAdId(Integer adId);

    CommentDto updateComment(Integer adId, Integer commentId, CommentDto createCommentDto, String username);

    void deleteComment(Integer adId, Integer commentId, String username);
}
