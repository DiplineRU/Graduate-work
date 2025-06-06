package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final AdServiceImpl adService;
    private final UserServiceImpl userService;
    private final AdRepository adRepository;
    private final AdMapper adMapper;
    private UserRepository userRepository;

    @Override
    public CommentDto addComment(Integer adId, CommentDto commentDto) {
        // 1. Получаем текущего пользователя из SecurityContext
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // 2. Получаем объявление
        Ad ad = adService.getAdEntity(adId);

        // 3. Создаем и сохраняем комментарий
        Comment comment = commentMapper.toEntity(commentDto);
        comment.setUser(user);
        comment.setAd(ad);
        comment.setAuthorFirstName(user.getFirstName());
        comment.setAuthorImage(user.getImage());
        comment.setCreatedAt(Instant.now());

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

        commentMapper.updateFromDto(createCommentDto, comment);
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
    public List<AdDto> getAllAds(int page) {
        return adRepository.findAll(PageRequest.of(page, 10))
                .map(adMapper::toDto)
                .toList();
    }
}
