package org.example.user.controller;

import org.example.user.requestobject.UserSaveRequest;
import org.example.user.requestobject.UserListRequest;
import org.example.user.requestobject.UserUpdateRequest;
import org.example.user.service.UserService;
import org.example.user.userview.UserView;
import org.example.user.userview.UserViewShort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/list", method = POST)
    List<UserViewShort> all(@Valid @RequestBody UserListRequest userListRequest) {
        return userService.all(userListRequest);
    }

    @RequestMapping(value = "/{id}", method = GET)
    UserView getById(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    @RequestMapping(value = "/save", method = POST)
    void save(@Valid @RequestBody UserSaveRequest userSaveRequest) {
        userService.save(userSaveRequest);
    }

    @RequestMapping(value = "/update", method = POST)
    void update(@Valid @RequestBody UserUpdateRequest userUpdateRequest) {
        userService.update(userUpdateRequest);
    }

}
