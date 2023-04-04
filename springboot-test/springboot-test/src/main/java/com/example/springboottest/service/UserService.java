package com.example.springboottest.service;

import com.example.springboottest.dao.UserDao;

import com.example.springboottest.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public boolean isExist(String username){
        User user = getByName(username);
        return null!=user;
    }

    public User getByName(String username){
        return userDao.findByUsername(username);
    }

    public User get(String username, String password){
        return userDao.getByUsernameAndAndPassword(username,password);
    }

    public void add(User user){
        userDao.save(user);
    }
}
