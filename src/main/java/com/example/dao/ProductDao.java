package com.example.dao;

import java.util.List;

import com.example.entity.Product;

public interface ProductDao {
	
	public List<Product> findAll();
	
	public List<Product> find(String searchWord);
	
	public String register(Product p);

}
