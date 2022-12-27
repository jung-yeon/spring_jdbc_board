package com.nhnacademy.jdbc.board.board.service;

import com.nhnacademy.jdbc.board.board.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> getComment(int boardId);
    List<Comment> getComments();
    void insert(Comment comment);
    void delete(int commentId);
    void deleteBoardId(int boardId);
}
