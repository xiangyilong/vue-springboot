package com.example.springboottest.pojo.controller;

import co.elastic.clients.elasticsearch.nodes.Http;
import com.example.springboottest.pojo.User;
import com.example.springboottest.result.Result;
import com.example.springboottest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(value = "api/register")
    @ResponseBody
    public Result register(@RequestBody User requestUser){
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);
        System.out.println("regesiter");
        User user = userService.getByName(username);
        if(user == null){
            User user1 = new User();
            user1.setUsername(username);
            user1.setPassword(requestUser.getPassword());
            userService.add(user1);
            return new Result(200);
        }else{
            return new Result(500);
        }
    }
}
