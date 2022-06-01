package com.example.service;

import java.util.List;

import com.example.entity.Product;

public interface ProductService {

	public List<Product> findAll();
	
	public List<Product> find(String searchWord);
	
	public String register(Product p);
}
