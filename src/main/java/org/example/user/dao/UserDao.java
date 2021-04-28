package org.example.user.dao;

import org.example.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

//public interface UserDao extends JpaRepository<User, Long> {} // это сработало!

public interface UserDao {

    /**
     * Get all of Users
     * @return
     */
    List<User> all();

    /**
     * Get user by id
     *
     * @param id
     * @return
     */
    User getById(Long id);

    void add(User newUser);
}
