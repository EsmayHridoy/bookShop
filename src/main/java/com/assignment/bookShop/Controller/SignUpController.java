package com.assignment.bookShop.Controller;

import com.assignment.bookShop.Model.User;
import com.assignment.bookShop.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/SignUp")
public class SignUpController {
    @Autowired
    UserService userService;

    @PostMapping
    public String addUser(@RequestParam("name") String name,
                           @RequestParam("email") String email,
                           @RequestParam("password") String password
                           ) {

        User user = new User();
        user.setUserEmail(email);
        user.setUserName(name);
        user.setPassword(password);
        user.setRole("General");
        boolean isRegistered = userService.createUser(user);
        System.out.println("i am in controller");
        if (isRegistered) {
            return "index";
        } else {
            return "signup";
        }
    }
}
