package com.example.service;

import com.example.entity.User;

public interface UserService {

	public User find(String login_id, String password);
}
