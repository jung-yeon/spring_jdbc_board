package com.nhnacademy.jdbc.board.board.service.impl;

import com.nhnacademy.jdbc.board.board.domain.Comment;
import com.nhnacademy.jdbc.board.board.mapper.CommentMapper;
import com.nhnacademy.jdbc.board.board.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultCommentService implements CommentService {
    private final CommentMapper commentMapper;

    public DefaultCommentService(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }


    @Override
    public List<Comment> getComment(int boardId) {
        return commentMapper.selectComment(boardId);
    }

    @Override
    public List<Comment> getComments() {
        return commentMapper.selectComments();
    }

    @Override
    public void insert(Comment comment) {
        commentMapper.insertComment(comment);
    }

    @Override
    public void delete(int commentId) {
        commentMapper.deleteById(commentId);
    }
    public void deleteBoardId(int boardId){commentMapper.deleteByboardId(boardId);}
}
