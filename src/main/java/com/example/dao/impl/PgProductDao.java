package com.example.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.dao.ProductDao;
import com.example.entity.Product;

@Repository
public class PgProductDao implements ProductDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	
//	@Autowired
//	private Product product;
	
	
	//SQL文
	private static final String SQL_SELECT_ALL = "SELECT p.id,p.product_id, p.name, p.price, c.name AS category, p.description FROM products p INNER JOIN categories c ON p.category_id = c.id ORDER BY p.product_id";
	
	private static final String SQL_SELECT = "SELECT p.id, p.product_id, p.name, p.price, c.name AS category, p.description FROM products p INNER JOIN categories c ON p.category_id = c.id WHERE ";
	private static final String ORDER = "ORDER BY p.product_id;";
	
    private static final String INSERT = "INSERT INTO products (product_id, category_id, name, price, description) VALUES (:product_id, :category_id, :name, :price, :description);";
    
    private static final String SQL = "SELECT product_id FROM products WHERE product_id = ?" ;
	
	List<Product> list = null;
	
	public List<Product> findAll(){
		String sql = SQL_SELECT_ALL;
		
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Product>(Product.class));
	}
	
	public List<Product> find(String searchWord) {
		
    	String WHERE = ("(p.name || c.name LIKE '%' || :searchWord || '%')");
        MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("searchWord", searchWord);

    	String SQL_FIND = SQL_SELECT + WHERE + ORDER;

    	return jdbcTemplate.query(SQL_FIND,param, new BeanPropertyRowMapper<Product>(Product.class));
		
	}
	
	public String register(Product p){
		
        MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("product_id", p.getProduct_id());
    	param.addValue("category_id", p.getCategory_id());
    	param.addValue("name", p.getName());
    	param.addValue("price", p.getPrice());
    	param.addValue("description", p.getDescription());
    	
    	jdbcTemplate.update(INSERT,param);
    	
    	return "更新に成功しました。";
    	
	}
	
	
	
}