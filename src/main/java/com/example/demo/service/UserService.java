package com.example.demo.service;


import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserService {


    @Autowired
    private UserDao dao;



    public void insertUser(User user){
        dao.insertUser(user);
    }


    public User printUser(Long id){
        return dao.selectUser(id);
    }


    public User selectUserByName(String name){
        return dao.selectUserByName(name);
    }
}
