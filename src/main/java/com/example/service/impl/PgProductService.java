package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.ProductDao;
import com.example.entity.Product;
import com.example.service.ProductService;

@Service
public class PgProductService implements ProductService {
	
	@Autowired
	private ProductDao productDao;
	
	@Override
	public List<Product> findAll(){
		
		return productDao.findAll();
	}
	
	@Override
	public List<Product> find(String searchWord){
		
		return productDao.find(searchWord);
	}
	
	@Override
	public String register(Product p){
		return productDao.register(p);
	}
}
