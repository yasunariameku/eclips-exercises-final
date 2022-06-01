package com.example.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.dao.UserDao;
import com.example.entity.User;

@Repository
public class PgUserDao implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    
    private String SQL_USER_MATCH = "SELECT * FROM users WHERE login_id = :login_id AND password = :password;";

    public User find(String login_id, String password) {
    	String sql = SQL_USER_MATCH;
    	
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("login_id",login_id);
    	param.addValue("password",password);

    	List<User> list = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<User>(User.class));
         
        return list.isEmpty() ? null : list.get(0);
    }
}
