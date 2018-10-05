//package com.controller;
//
//import com.entity.User;
//import com.service.user.impl.UserServiceImpl;
//import com.service.user.service.user.impl.UserServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class UserController {
//    @Autowired
//    private UserServiceImpl.UserService userService;
//    @GetMapping("/user")
//    public User getInfo(){
//         String username="jhc";
//         String password="123456";
//         return userService.login(username,password);
//    }
//}
