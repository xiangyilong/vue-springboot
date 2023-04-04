package com.example.springboottest.controller;

import com.example.springboottest.pojo.Book;
import com.example.springboottest.service.BookService;
import com.example.springboottest.util.StringUtils;
import org.aspectj.apache.bcel.classfile.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class LibraryController {
    @Autowired
    BookService bookService;
    @GetMapping("/api/books")
    public List<Book> list() throws Exception{
        return bookService.list();
    }
    @PostMapping("/api/books")
    public Book addOrUpdate(@RequestBody Book book) throws Exception{
        bookService.addOrUpdate(book);
        return book;
    }
    @PostMapping("/api/delete")
    public void delete(@RequestBody Book book) throws Exception{
        bookService.deleteById(book.getId());
    }
    @GetMapping("/api/category/{cid}/books")
    public List<Book> listByCategory(@PathVariable("cid") int cid) throws Exception{
        if(0 != cid){
            return bookService.listByCategory(cid);
        }else {
            return list();
        }
    }
    @CrossOrigin
    @GetMapping("/api/search")
    public List<Book> searchResult(@RequestParam("keywords") String keywords){
        if("".equals(keywords)) {
            return bookService.list();
        }else {
            return bookService.Search(keywords);
        }
    }
    @CrossOrigin
    @PostMapping("api/covers")
    public String coversUpload(MultipartFile file) throws Exception{
        String folder = "C:/Users/201/IdeaProjects/Img";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, StringUtils.getRandomString(6) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length()-4));
        if(!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            String imgURL = "http://localhost:8443/api/file/" + f.getName();
            return imgURL;
        }catch (IOException e){
            e.printStackTrace();
            return "";
        }
    }
}
