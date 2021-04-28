package org.example.user.service;

import org.example.user.dto.UserDto;
import org.example.user.model.User;

import java.util.List;

public interface UserService {

    List<UserDto> all();

    UserDto getById(Long id);

    void add(User newUser);
}
