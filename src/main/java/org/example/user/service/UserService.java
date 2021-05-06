package org.example.user.service;

import org.example.user.requestobject.UserSaveRequest;
import org.example.user.requestobject.UserListRequest;
import org.example.user.requestobject.UserUpdateRequest;
import org.example.user.userview.UserView;
import org.example.user.userview.UserViewShort;

import java.util.List;
import java.util.Map;

public interface UserService {

    /**
     * Get all of users according to parameter from request body
     * @return
     */
    List<UserViewShort> all(UserListRequest userListRequest);

    /**
     * Get user by id
     * @param id
     * @return
     */
    UserView getById(Long id);

    /**
     * Add a new user to db
     * @param userSaveRequest
     * @return
     */
    void save(UserSaveRequest userSaveRequest);


    /**
     * Update an existing user
     * @param userUpdateRequest
     * @return
     */
    void update(UserUpdateRequest userUpdateRequest);
}
