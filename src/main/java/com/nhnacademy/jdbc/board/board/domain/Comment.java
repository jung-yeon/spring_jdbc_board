package com.nhnacademy.jdbc.board.board.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Comment {
    private int commentId;
    private int boardId;
    private String comments;

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    private String writer;

    public Comment(int boardId, String comments, String writer) {
        this.boardId = boardId;
        this.comments = comments;
        this.writer = writer;
    }


    public int getCommentId() {
        return commentId;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
