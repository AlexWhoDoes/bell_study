package org.example.user.controller;

import org.example.user.dto.UserDto;
import org.example.user.model.User;
import org.example.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list") //should be POST method and filter to extract exact users from DB
    List<UserDto> all() {// now it shows just all users from DB
        return userService.all();
    }

    @RequestMapping(value = "/{id}", method = GET)
    UserDto getById(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    @RequestMapping(value = "/save", method = POST)
    public void person(@RequestBody User newUser) {
        System.out.println(newUser.getOffice().getId());
        userService.add(newUser);
    }




}
