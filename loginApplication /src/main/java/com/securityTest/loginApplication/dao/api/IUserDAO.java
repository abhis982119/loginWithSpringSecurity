package com.securityTest.loginApplication.dao.api;

import com.securityTest.loginApplication.entity.UserEntity;

public interface IUserDAO {


    public UserEntity getUser(String userName);

    public void saveUser(UserEntity user);
}
