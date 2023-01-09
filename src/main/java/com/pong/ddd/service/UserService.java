package com.pong.ddd.service;

import com.pong.ddd.model.Role;
import com.pong.ddd.model.User;
import com.pong.ddd.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public class UserService {

    @Autowired
    private UserRespository userRespository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User save(User user){
        String endcodedPassword = passwordEncoder.encode(user.getPassword()); // 패스워드가 인코더 됨
        user.setPassword(endcodedPassword);
        user.setEnabled(true);
        Role role = new Role();
        role.setId(1L);
        user.getRoles().add(role);
        return userRespository.save(user);

    }



}
