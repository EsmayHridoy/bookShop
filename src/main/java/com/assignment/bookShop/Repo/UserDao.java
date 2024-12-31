package com.assignment.bookShop.Repo;

import com.assignment.bookShop.Model.User;
import org.springframework.stereotype.Component;


@Component
public interface UserDao {

    public boolean insertUser(User user);
    public User findUserByEmail(String email);
}
