package com.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.UserDao;
import com.example.entity.User;
import com.example.service.UserService;

@Service
public class PgUserService implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
    public User find(String login_id, String password) {
        return userDao.find(login_id, password);
    }
}
