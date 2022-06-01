package com.example.dao;

import com.example.entity.User;

public interface UserDao {

    public User find(String login_id, String password);


}
