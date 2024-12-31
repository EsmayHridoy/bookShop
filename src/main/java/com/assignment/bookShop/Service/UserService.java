package com.assignment.bookShop.Service;


import com.assignment.bookShop.Model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    public boolean createUser(User user);
    public boolean loginCheck(User user);
}
