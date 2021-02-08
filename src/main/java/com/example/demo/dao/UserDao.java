package com.example.demo.dao;


import com.example.demo.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public class UserDao {

    @PersistenceContext
    private EntityManager sessionFactory;

    public void insertUser(User user){
        sessionFactory.persist(user);
    }

    public User selectUser(Long id){
        return sessionFactory.find(User.class, id);
    }

    public User selectUserByName(String name){
        User user = null;
        try {
            user = (User) sessionFactory.createQuery("select user from User user where user.name=:name")
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (Exception x){

        }
        return user;
    }
}
