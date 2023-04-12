package com.example.springboottest.dao;

import com.example.springboottest.pojo.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordDao extends JpaRepository<Record,Integer> {
    List<Record> findAllByUsername(String username);

    List<Record> findByStateLike(String state);

    List<Record> findByStateNotLike(String state);
}
