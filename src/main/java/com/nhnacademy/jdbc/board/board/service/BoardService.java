package com.nhnacademy.jdbc.board.board.service;

import com.nhnacademy.jdbc.board.board.domain.Board;
import com.nhnacademy.jdbc.board.board.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface BoardService {
    Optional<Board> getBoard(int boardId);
    Integer getBoardComment(int boardId);
    List<Board> getBoards();
    List<Board> getBoardsPaging(Page page);

    void insert(Board board);

    void update(String userId,
                String title,
                String content,
                String modifier,
                Date modifyDate,
                int boardId);

    void updateContent(Board board);

    void delete(int boardId);
}
