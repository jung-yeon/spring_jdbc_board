package com.nhnacademy.jdbc.board.user.service.impl;

import com.nhnacademy.jdbc.board.user.mapper.UserMapper;
import com.nhnacademy.jdbc.board.user.domain.User;
import com.nhnacademy.jdbc.board.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultUserService implements UserService {

    private final UserMapper userMapper;

    public DefaultUserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Optional<User> getUser(String userId) {
        return userMapper.selectUser(userId);
    }

    @Override
    public List<User> getUsers() {
        return userMapper.selectUsers();
    }

    @Override
    public void insert(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public void update(String password, String name, String userId) {
        userMapper.updateById(password, userId);
    }

    @Override
    public void delete(String userId) {
        userMapper.deleteById(userId);
    }
}
