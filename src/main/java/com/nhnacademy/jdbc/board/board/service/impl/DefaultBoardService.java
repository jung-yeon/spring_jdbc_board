package com.nhnacademy.jdbc.board.board.service.impl;

import com.nhnacademy.jdbc.board.board.domain.Board;

import com.nhnacademy.jdbc.board.board.domain.Page;
import com.nhnacademy.jdbc.board.board.mapper.BoardMapper;
import com.nhnacademy.jdbc.board.board.service.BoardService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultBoardService implements BoardService {
    private final BoardMapper boardMapper;


    public DefaultBoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }


    @Override
    public Optional<Board> getBoard(int boardId) {
        return boardMapper.selectBoard(boardId);
    }

    @Override
    public Integer getBoardComment(int boardId) {
        return boardMapper.BoardComment(boardId);
    }

    @Override
    public List<Board> getBoards() {
        return boardMapper.selectBoards();
    }

    @Override
    public List<Board> getBoardsPaging(Page page) {
        return boardMapper.selectBoardsPaging(page);
    }

    @Override
    public void insert(Board board) {
        boardMapper.insertBoard(board);
    }



    @Override
    public void update(String userId, String title, String content, String modifier, Date modifyDate, int boardId) {
        boardMapper.updateById(userId, title, content, modifier, modifyDate, boardId);
    }

    @Override
    public void updateContent(Board board){
        boardMapper.updateContentById(board);
    }

    @Override
    public void delete(int boardId) {
        boardMapper.deleteById(boardId);
    }
}
