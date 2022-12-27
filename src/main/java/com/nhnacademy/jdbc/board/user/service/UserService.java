package com.nhnacademy.jdbc.board.user.service;

import com.nhnacademy.jdbc.board.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getUser(String userId);
    List<User> getUsers();
    void insert(User user);

    void update(String password, String name, String userId);

    void delete(String userId);
}
