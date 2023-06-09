package com.example.springboottest.pojo.controller;

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

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(value="api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser, HttpSession session)
    {
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);
        User user =userService.get(username,requestUser.getPassword());
        if(null == user){
            return new Result(400);
        }else {
            session.setAttribute("user",user);
            Result result = new Result(200);
            if(username == "admin"){
                result.setPrevilege(true);
            }
            return result ;
        }
    }

}
