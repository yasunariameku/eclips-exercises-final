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
	public Product findById(Integer id){
		
		return productDao.findById(id);
	}
	
	@Override
	public String register(Product p){
		return productDao.register(p);
	}
	
	@Override
	public String delete(Integer i){
		return productDao.delete(i);
	}
	
	@Override
	public Product check(Integer id, Integer product_id){
		return productDao.check(id, product_id);
	}
	
	@Override
	public String update(){
		return productDao.update();
	}
}
