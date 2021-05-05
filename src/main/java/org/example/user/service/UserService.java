package org.example.user.service;

import org.example.user.requestobject.UserSaveRequest;
import org.example.user.requestobject.UserListRequest;
import org.example.user.requestobject.UserUpdateRequest;
import java.util.List;
import java.util.Map;

public interface UserService {

    /**
     * Get all of users according to parameter from request body
     * @return
     */
    List<Map<String, String>> all(UserListRequest userListRequest);

    /**
     * Get user by id
     * @param id
     * @return
     */
    Map<String, String> getById(Long id);

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
