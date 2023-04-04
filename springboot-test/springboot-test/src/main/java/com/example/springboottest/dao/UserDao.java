package com.example.springboottest.dao;

import com.example.springboottest.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {
    User findByUsername(String username);
    User getByUsernameAndAndPassword(String username,String password);
}
