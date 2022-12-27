package com.nhnacademy.jdbc.board.user.domain;

import java.time.LocalDateTime;

public class User {
    private final String userId;
    private final String password;
    private final UserType type;

    public User(String userId, String password, UserType type) {
        this.userId = userId;
        this.password = password;
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public UserType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
