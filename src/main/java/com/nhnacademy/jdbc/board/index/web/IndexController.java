package com.nhnacademy.jdbc.board.index.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping
@Slf4j
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "index/index";
    }
}
