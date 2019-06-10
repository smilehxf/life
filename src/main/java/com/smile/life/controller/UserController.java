package com.smile.life.controller;


import com.smile.life.entity.User;
import com.smile.life.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    FoodService foodService;
    @Autowired
    ActivityService activityService;
    @Autowired
    CollectFoodService collectFoodService;
    @Autowired
    ReservationService reservationService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/{username}")
    public Boolean find(@PathVariable String username) {
        User user = userService.findOne(username);
        return user != null;
    }

    @PostMapping("/register")
    public String add(String username, String password) {
        User user = new User(username, bCryptPasswordEncoder.encode(password));
        user.setRole("ROLE_USER");
        userService.add(user);
        return "注册成功！";
    }

    @GetMapping("/login/error")
    public String loginError(HttpServletRequest request) {
        AuthenticationException exception =
                (AuthenticationException) request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        switch (exception.getClass().getSimpleName()) {
            case "InternalAuthenticationServiceException":
                return "服务器内部错误！";
            case "BadCredentialsException":
                return "用户不存在或密码错误！";
            default:
                return exception.getClass().getSimpleName();
        }
    }

    @GetMapping("/update")
    public String update(String username, String password, String newPassword) {
        User user = userService.findOne(username);
        if (user == null) {
            return "用户不存在！";
        } else if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return "密码不正确！";
        }
        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        userService.save(user);
        return "修改成功！";
    }

}


