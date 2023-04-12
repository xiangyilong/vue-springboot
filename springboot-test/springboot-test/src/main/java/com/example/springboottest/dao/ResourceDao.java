package com.example.springboottest.dao;

import com.example.springboottest.pojo.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResourceDao extends JpaRepository<Resource,Integer> {
}
