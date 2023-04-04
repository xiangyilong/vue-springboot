package com.example.springboottest.dao;

import com.example.springboottest.pojo.Book;
import com.example.springboottest.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookDao extends JpaRepository<Book,Integer> {
    List<Book> findAllByCategory(Category category);
    List<Book> findAllByTitleLikeOrAuthorLike(String keyword1,String keyword2);
}
