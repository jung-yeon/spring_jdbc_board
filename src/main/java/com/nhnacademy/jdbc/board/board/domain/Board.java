package com.nhnacademy.jdbc.board.board.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Board {
    private int boardId;
    private String userId;
    private String title;
    private String content;
    private String modifier;
    private Date createdAt;
    private Date modifyDate;


    public int getCommentCnt() {
        return commentCnt;
    }

    public void setCommentCnt(int commentCnt) {
        this.commentCnt = commentCnt;
    }

    private int commentCnt;



    public int getBoardId() {
        return boardId;
    }

    public String getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getModifier() {
        return modifier;
    }


    public Board(int boardId, String userId, String title, String content, String modifier, Date createdAt, Date modifyDate, int commentCnt) {
        this.boardId = boardId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.modifier = modifier;
        this.createdAt = createdAt;
        this.modifyDate = modifyDate;
        this.commentCnt = commentCnt;
    }

    public Date getModifyDate() {
        return modifyDate;
    }


    public void setContent(String content){
        this.content = content;
    }
    public void setModifyDate(Date modifyDate){
        this.modifyDate = modifyDate;
    }
    public void setModifier(String modifier){
        this.modifier = modifier;
    }

    @Override
    public String toString() {
        return "Board{" +
                "boardId=" + boardId +
                ", userId='" + userId + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", modifier='" + modifier + '\'' +
                ", writeDate=" + createdAt +
                ", modifyDate=" + modifyDate +
                '}';
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

}
