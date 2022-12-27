package com.nhnacademy.jdbc.board.board.controller;

import com.nhnacademy.jdbc.board.board.domain.Board;
import com.nhnacademy.jdbc.board.board.domain.Comment;
import com.nhnacademy.jdbc.board.board.domain.Page;
import com.nhnacademy.jdbc.board.board.service.BoardService;
import com.nhnacademy.jdbc.board.board.service.CommentService;
import com.nhnacademy.jdbc.board.user.domain.User;
import com.nhnacademy.jdbc.board.user.domain.UserType;
import com.nhnacademy.jdbc.board.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class BoardController {
    private final BoardService boardService;
    private final CommentService commentService;
    private final UserService userService;
    private final Page page = new Page(0, 19);
//    private final int FIRST_NUM = 1;
//    private final int PLUS_NUM = 20;

    public BoardController(BoardService boardService, CommentService commentService, UserService userService) {
        this.boardService = boardService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping("/board")
    public String boardform(Model model) {
        List<Board> boards = boardService.getBoardsPaging(page);
        List<Board> commentCnts = new ArrayList<>();
        for (Board board : boards) {
            board = boardService.getBoard(board.getBoardId()).get();
            board.setCommentCnt(boardService.getBoardComment(board.getBoardId()));
            commentCnts.add(board);
        }
        model.addAttribute("next", "board/boardForm");
        model.addAttribute("boards", commentCnts);
        return "board/boardForm";
    }

    @GetMapping("/board/after")
    public String aftergetboardform(
            Model model) {
        int pagenum = page.getPageNo() + 19;
        page.setPageNo(pagenum);
        List<Board> boards = boardService.getBoardsPaging(page);
        List<Board> commentCnts = new ArrayList<>();
        if (boards.isEmpty()) {
            page.setPageNo(pagenum - 19);
            boards = boardService.getBoardsPaging(page);
            for (Board board : boards) {
                board = boardService.getBoard(board.getBoardId()).get();
                board.setCommentCnt(boardService.getBoardComment(board.getBoardId()));
                commentCnts.add(board);
            }
            model.addAttribute("msg", "다음장이 없습니다.");
            model.addAttribute("boards", commentCnts);
            model.addAttribute("url", "/board");
            return "alert";
        }

        for (Board board : boards) {
            board = boardService.getBoard(board.getBoardId()).get();
            board.setCommentCnt(boardService.getBoardComment(board.getBoardId()));
            commentCnts.add(board);
        }

        model.addAttribute("boards", commentCnts);
        return "board/boardForm";
    }

    @GetMapping("/board/before")
    public String beforegetboardform(
            Model model) {
        int pagenum = page.getPageNo() - 19;
        List<Board> boards;
        List<Board> commentCnts = new ArrayList<>();
        if (pagenum < 0) {
            pagenum = page.getPageNo();
            page.setPageNo(pagenum);
            boards = boardService.getBoardsPaging(page);
            commentCnts = new ArrayList<>();
            for (Board board : boards) {
                board = boardService.getBoard(board.getBoardId()).get();
                board.setCommentCnt(boardService.getBoardComment(board.getBoardId()));
                commentCnts.add(board);
            }
            model.addAttribute("msg", "이전장이 없습니다.");
            model.addAttribute("boards", commentCnts);
            model.addAttribute("url", "/board");
            return "alert";
        }
        page.setPageNo(pagenum);
        boards = boardService.getBoardsPaging(page);
        for (Board board : boards) {
            board = boardService.getBoard(board.getBoardId()).get();
            board.setCommentCnt(boardService.getBoardComment(board.getBoardId()));
            commentCnts.add(board);
        }

        model.addAttribute("boards", commentCnts);
        return "board/boardForm";
    }

    @GetMapping("/board/{boardId}")
    public String boardIdform(@PathVariable int boardId,
                              @CookieValue(value = "CSESSION", required = false) String csession,
                              HttpServletRequest req,
                              Model model) {
        HttpSession session = req.getSession();
        String sessionstr = String.valueOf(session.getAttribute(csession));
        Board board = boardService.getBoard(boardId).get();
        List<Comment> comment = commentService.getComment(boardId);
        model.addAttribute("commentList", comment);
        model.addAttribute("name", sessionstr);
        model.addAttribute("board", board);

        return "board/boardList";
    }

    @GetMapping("/board/register")
    public String boardRegister() {
        return "board/boardRegister";
    }

    @PostMapping("/board/register")
    public String postboardRegister(@RequestParam("title") String title,
                                    @RequestParam("content") String contents,
                                    @CookieValue(value = "CSESSION", required = false) String csession,
                                    HttpServletRequest req,
                                    Model model) {
        Board board = new Board();
        if (csession.isEmpty()) {
            model.addAttribute("msg", "로그인 먼저 해주세요");
            model.addAttribute("url", "/login");
            return "alert";
        }
        board.setContent(contents);
        board.setTitle(title);
        board.setUserId(csession);
        board.setCreatedAt(new Date());
        boardService.insert(board);
        List<Board> boards = boardService.getBoardsPaging(page);
        List<Board> commentCnts = new ArrayList<>();
        for (Board board1 : boards) {
            board1 = boardService.getBoard(board1.getBoardId()).get();
            board1.setCommentCnt(boardService.getBoardComment(board1.getBoardId()));
            commentCnts.add(board1);
        }
        model.addAttribute("boards", commentCnts);
        return "/board/boardForm";
    }

    @PostMapping("/board/{boardId}/comment")
    public String CommentRegister(@PathVariable int boardId,
                                  @RequestParam("comment") String commentstr,
                                  @CookieValue(value = "CSESSION", required = false) String csession,
                                  HttpServletRequest req,
                                  Model model) {

        Board board = boardService.getBoard(boardId).get();
        Comment comment = new Comment();
        List<Comment> commentList = commentService.getComment(boardId);
        comment.setBoardId(boardId);
        comment.setComments(commentstr);
        comment.setWriter(csession);
        commentService.insert(comment);
        model.addAttribute("commentList", commentList);
        model.addAttribute("board", board);
        return "board/boardList";

    }

    @GetMapping("/board/{boardId}/comment")
    public String getCommentRegister(@PathVariable int boardId,
                                     Model model) {
        Board board = boardService.getBoard(boardId).get();
        List<Comment> commentList = commentService.getComments();
        model.addAttribute("commentList", commentList);
        model.addAttribute("board", board);
        return "board/boardList";

    }

    @GetMapping("/board/{boardId}/modify")
    public String boardModify(@PathVariable int boardId,
                              @CookieValue(value = "CSESSION", required = false) String csession,
                              HttpServletRequest req,
                              Model model) {
        Board board = boardService.getBoard(boardId).get();
        Optional<User> user = userService.getUser(csession);
        if (user.isPresent()) {
            User userGet = user.get();
            if (csession.equals(board.getUserId()) || userGet.getType().equals(UserType.CS)) {
                model.addAttribute("board", board);
                return "board/boardModify";
            } else {
                model.addAttribute("msg", "권한이 없습니다.");
                model.addAttribute("url", "/board");
            }
        } else {
            model.addAttribute("msg", "권한이 없습니다.");
            model.addAttribute("url", "/board");
        }
        return "alert";

    }

    @PostMapping("/board/{boardId}/modify")
    public String postboardModify(@PathVariable int boardId,
                                  @RequestParam("content") String content,
                                  @CookieValue(value = "CSESSION", required = false) String csession,
                                  HttpServletRequest req,
                                  Model model) {
        Board board = boardService.getBoard(boardId).get();
        List<Comment> comment = commentService.getComment(boardId);
        Optional<User> user = userService.getUser(csession);
        if (user.isPresent()) {
            User userGet = user.get();
            if (csession.equals(board.getUserId()) || userGet.getType().equals(UserType.CS)) {
                model.addAttribute("comment", comment);
                board.setContent(content);
                board.setModifyDate(new Date());
                board.setModifier(csession);
                boardService.updateContent(board);
                model.addAttribute("board", board);

                return "board/boardList";
            } else {
                model.addAttribute("msg", "권한이 없습니다.");
                model.addAttribute("url", "/board");
            }
            return "alert";
        } else {
            model.addAttribute("msg", "권한이 없습니다.");
            model.addAttribute("url", "/board");
        }
        return "alert";
    }

    @GetMapping("board/{boardId}/delete")
    public String getdeleteBoard(@PathVariable int boardId,
                                 @CookieValue(value = "CSESSION", required = false) String csession,
                                 Model model) {
        Optional<User> user = userService.getUser(csession);
        Board currentBoard = boardService.getBoard(boardId).get();
        List<Board> boards;
        List<Board> commentCnts = new ArrayList<>();
        if (user.isPresent()) {
            User userGet = user.get();
            if (csession.equals(currentBoard.getBoardId()) || userGet.getType().equals(UserType.CS)) {
                commentService.deleteBoardId(boardId);
                boardService.delete(boardId);
                boards = boardService.getBoardsPaging(page);
                for (Board board : boards) {
                    board = boardService.getBoard(board.getBoardId()).get();
                    board.setCommentCnt(boardService.getBoardComment(board.getBoardId()));
                    commentCnts.add(board);
                }
                model.addAttribute("boards", commentCnts);
                return "board/boardForm";
            } else {
                model.addAttribute("msg", "권한이 없습니다.");
                model.addAttribute("url", "/board");
                return "alert";
            }
        } else {
            model.addAttribute("msg", "권한이 없습니다.");
            model.addAttribute("url", "/board");
        }
        return "alert";


    }
}
