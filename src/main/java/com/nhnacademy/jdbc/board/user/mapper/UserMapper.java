package com.nhnacademy.jdbc.board.user.mapper;

import com.nhnacademy.jdbc.board.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

public interface UserMapper {
    Optional<User> selectUser(String userId);
    List<User> selectUsers();
    void insertUser(User user);

    void updateById(String password, String userId);

    void deleteById(String userId);
}
