package com.securityTest.loginApplication.service;

import com.securityTest.loginApplication.dao.impl.UserDao;
import com.securityTest.loginApplication.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.getUser(username);


        return User.builder()
                .username(userEntity.getUserName())
                .password(userEntity.getPasswored()) // Assuming the password is already encoded
               // .roles("USER") // Add roles as needed
                .build();
    }
}
