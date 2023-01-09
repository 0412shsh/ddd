package com.pong.ddd.controller;

import com.pong.ddd.model.User;
import com.pong.ddd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(){
        return "account/login";
    }

    @GetMapping("/register")
    public String register(){
        return "account/register";
    }

   @PostMapping("/register")
    public String register(User user){ //전송된 form에 데이터가 파라미터로 자동으로 User에 저장됨
        userService.save(user);
        return "redirect:/";
    }


}
