package com.nhnacademy.jdbc.board.user.domain;

public enum UserType {
    CS{
        @Override
        public String toString() {
            return "CS";
        }
    },
    CUSTOMER{
        @Override
        public String toString() {
            return "CUSTOMER";
        }
    }

}
