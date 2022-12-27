package com.nhnacademy.jdbc.board.board.mapper;

import com.nhnacademy.jdbc.board.board.domain.Board;
import com.nhnacademy.jdbc.board.board.domain.Page;
import org.apache.ibatis.annotations.Param;


import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BoardMapper {
    Optional<Board> selectBoard(int boardId);
    Integer BoardComment(int boardId);
    List<Board> selectBoards();
    List<Board> selectBoardsPaging(Page page);
    void insertBoard(Board board);

    void updateById(String userId, String title, String content, String modifier, Date modifyDate, int boardId);
    void updateContentById(@Param("board") Board board);
    void deleteById(int boardId);
}
