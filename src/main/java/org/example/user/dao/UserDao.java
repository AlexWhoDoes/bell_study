package org.example.user.dao;

import org.example.user.requestobject.UserListRequest;
import org.example.user.model.User;

import java.util.List;


public interface UserDao {

    /**
     * Get all of users according to parameter from request body
     * @return
     */
    List<User> all(UserListRequest userListRequest);

    /**
     * Get user by id
     * @param id
     * @return
     */
    User getById(Long id);

    /**
     * Add or update a  user
     * @param newUser
     * @return
     */
    Long addOrUpdate(User newUser);


}
