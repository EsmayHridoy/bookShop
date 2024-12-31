package com.assignment.bookShop.Config;


import com.assignment.bookShop.Repo.UserDao;
import com.assignment.bookShop.Utils.HashPassword;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;




@Configuration
@EnableWebSecurity
public class SecurityConfig{
    private final UserDao userDao;
    private final HashPassword hashPassword;

    public SecurityConfig(UserDao userDao, HashPassword hashPassword) {
        this.userDao = userDao;
        this.hashPassword = hashPassword;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return hashPassword.hashPassword(rawPassword.toString());
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {

                return hashPassword.verifyPassword(rawPassword.toString(), encodedPassword);
            }
        };
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
            System.out.println("Hello I am in security Config");
            com.assignment.bookShop.Model.User user = userDao.findUserByEmail(email);
            System.out.println(user.getUserName());
            if (user == null) {
                throw new UsernameNotFoundException("User not found with email: " + email);
            }
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUserEmail())
                    .password(user.getPassword())
                    .roles(user.getRole())
                    .build();
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/BookShop", "/SignUp","/signin").permitAll()
                        .requestMatchers("/dashboard").authenticated()
                        .requestMatchers("/homepage").authenticated()
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/BookShop")
                        .defaultSuccessUrl("/homepage", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/BookShop")
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );

        return http.build();
    }


//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .requestMatchers("/BookShop", "/SignUp").permitAll() // allow access to these URLs
//                .requestMatchers("/homepage").authenticated() // require authentication for /homepage
//                .anyRequest().authenticated() // require authentication for any other request
//                .and()
//                .formLogin()
//                .loginPage("/BookShop")
//                .loginProcessingUrl("/signin")
//                .defaultSuccessUrl("/homepage", true)
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/BookShop")
//                .deleteCookies("JSESSIONID")
//                .permitAll();
//        return http.build();
//    }

}
