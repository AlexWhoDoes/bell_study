package org.example.user.service;

import org.example.user.dao.UserDao;
import org.example.user.dto.UserDto;
import org.example.user.mapper.Mapper;
import org.example.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService{

    private UserDao userDao;
    private Mapper mapper;

    @Autowired
    public UserServiceImp(UserDao userDao, Mapper mapper) {
        this.userDao = userDao;
        this.mapper = mapper;
    }

    @Override
    public List<UserDto> all() {
        List<User> in = userDao.all();
        return mapper.all(in);
    }

    @Override
    public UserDto getById(Long id) {
        User user = userDao.getById(id);
        return mapper.getById(user);
    }

    @Override
    @Transactional
    public void add(User newUser) {
        userDao.add(newUser);
    }
}
