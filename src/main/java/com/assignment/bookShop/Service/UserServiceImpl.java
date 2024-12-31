package com.assignment.bookShop.Service;


import com.assignment.bookShop.Model.User;
import com.assignment.bookShop.Repo.UserDao;
import com.assignment.bookShop.Utils.HashPassword;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;

    @Autowired
    HashPassword hashPassword;


    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    private PasswordEncoder passwordEncoder;


    public boolean createUser(User user){
        user.setPassword(HashPassword.hashPassword(user.getPassword()));
        System.out.println("I am in service");
        boolean flag = userDao.insertUser(user);
        return flag;
    }

    public boolean loginCheck(User user) {
        System.out.println("Hello I am login check service");
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserEmail());

            System.out.println(userDetails.getPassword());
            System.out.println(HashPassword.hashPassword(user.getPassword()));
            if (passwordEncoder.matches(user.getPassword(), userDetails.getPassword())) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
