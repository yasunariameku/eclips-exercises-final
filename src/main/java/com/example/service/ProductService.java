package com.example.service;

import java.util.List;

import com.example.entity.Product;

public interface ProductService {

	public List<Product> findAll();
	
	public List<Product> find(String searchWord);
	
	public Product findById(Integer id);
	
	public String register(Product p);
	
	public String delete(Integer i);
	
	public Product check(Integer id, Integer product_id);
	
	public String update(Product updateProduct);
	
	
}
