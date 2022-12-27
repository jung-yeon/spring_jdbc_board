package com.nhnacademy.jdbc.board.board.domain;

import lombok.Data;

@Data
public class Page {

    private int pageNo;

    private int pageAmount;


    public Page(int pageNo, int pageAmount) {
        this.pageNo = pageNo;
        this.pageAmount = pageAmount;
    }
}