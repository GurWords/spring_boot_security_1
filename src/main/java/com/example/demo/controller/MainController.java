package com.example.demo.controller;


import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/")
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("printUser/{id}")
    public ResponseEntity<User> printUser(@PathVariable Long id){
        User user = userService.printUser(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("insertUser")
    public ResponseEntity<User> insertUser(){
        User user = new User();
        user.setName("Any");
        user.setPassword("123");
        user.setRole("USER");
        userService.insertUser(user);
        return ResponseEntity.ok(user);
    }
}
