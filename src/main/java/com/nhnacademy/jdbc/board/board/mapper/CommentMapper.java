package com.nhnacademy.jdbc.board.board.mapper;

import com.nhnacademy.jdbc.board.board.domain.Board;
import com.nhnacademy.jdbc.board.board.domain.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CommentMapper {
    List<Comment> selectComment(int boardId);

    List<Comment> selectComments();
    void insertComment(Comment comment);
    void deleteById(int commentId);
    void deleteByboardId(int boardId);
}

