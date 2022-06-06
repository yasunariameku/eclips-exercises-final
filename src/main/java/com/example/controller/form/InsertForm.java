package com.example.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class InsertForm {
	
	@NotNull
	private Integer product_id;
	
	@NotBlank
	private String name;
	
	@NotNull
	private Integer price;
	
	private Integer category_id;
	private String description;
	
	
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer prosuct_id) {
		this.product_id = prosuct_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	

}
