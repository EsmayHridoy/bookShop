package com.assignment.bookShop.Controller;


import com.assignment.bookShop.Model.User;
import com.assignment.bookShop.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



import java.util.HashMap;
import java.util.Map;

@Controller
public class Index {

    @Autowired
    private UserService userService;

    @RequestMapping("/BookShop")
    public String index(){
        return "index";
    }

    @GetMapping("/SignUp")
    public String signupButton(){
        return "signup";
    }



    @PostMapping("/signin")
    public String  signIn(@RequestParam("email") String email,
                          @RequestParam("password") String password) {

        User user = new User();
        user.setPassword(password);
        user.setUserEmail(email);
        System.out.println("Hello I am signin controller");
        if (userService.loginCheck(user)) {

            return "homepage";
        } else {
            return "index";
        }

    }


}
