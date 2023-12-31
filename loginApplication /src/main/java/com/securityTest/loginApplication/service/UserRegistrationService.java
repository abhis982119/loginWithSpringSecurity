package com.securityTest.loginApplication.service;

import com.securityTest.loginApplication.dao.api.IUserDAO;
import com.securityTest.loginApplication.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {


    @Autowired
    private IUserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveUser(String userName, String password){
    UserEntity user = UserEntity.builder().userName(userName).passwored(passwordEncoder.encode(password)).
            build();
    userDAO.saveUser(user);

    }
}
