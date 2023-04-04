package com.example.springboottest.service;

import com.example.springboottest.dao.BookDao;
import com.example.springboottest.pojo.Book;
import com.example.springboottest.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.List;

public class BookService {
    @Autowired
    BookDao bookDao;
    @Autowired
    CategoryService categoryService;
    public List<Book> list(){
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        return bookDao.findAll(sort);
    }
    public void addOrUpdate(Book book){
        bookDao.save(book);
    }
    public void deleteById(int id){
        bookDao.deleteById(id);
    }
    public List<Book> listByCategory(int cid){
        Category category = categoryService.get(cid);
        return bookDao.findAllByCategory(category);
    }
    public List<Book> Search(String keywords){
        return bookDao.findAllByTitleLikeOrAuthorLike('%' + keywords + '%', '%' + keywords + '%');
    }
}
