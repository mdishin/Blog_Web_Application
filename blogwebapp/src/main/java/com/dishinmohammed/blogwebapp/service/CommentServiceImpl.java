package com.dishinmohammed.blogwebapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dishinmohammed.blogwebapp.dto.CommentDto;
import com.dishinmohammed.blogwebapp.entity.Comment;
import com.dishinmohammed.blogwebapp.entity.Post;
import com.dishinmohammed.blogwebapp.entity.User;
import com.dishinmohammed.blogwebapp.mapper.CommentMapper;
import com.dishinmohammed.blogwebapp.repository.CommentRepository;
import com.dishinmohammed.blogwebapp.repository.PostRepository;
import com.dishinmohammed.blogwebapp.repository.UserRepository;
import com.dishinmohammed.blogwebapp.util.SecurityUtils;


@Service
public class CommentServiceImpl implements CommentService {
	
	private CommentRepository commentRepository;
    private PostRepository postRepository;
    private UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository,
                              PostRepository postRepository,
                              UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createComment(String postUrl, CommentDto commentDto) {

        Post post = postRepository.findByUrl(postUrl).get();
        Comment comment = CommentMapper.mapToComment(commentDto);
        comment.setPost(post);
        commentRepository.save(comment);
    }

    @Override
    public List<CommentDto> findAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream()
                .map(CommentMapper::mapToCommentDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public List<CommentDto> findCommentsByPost() {
        String email = SecurityUtils.getCurrentUser().getUsername();
        User createdBy = userRepository.findByEmail(email);
        Long userId = createdBy.getId();
        List<Comment> comments = commentRepository.findCommentsByPost(userId);
        return comments.stream()
                .map((comment) -> CommentMapper.mapToCommentDto(comment))
                .collect(Collectors.toList());
    }

}
