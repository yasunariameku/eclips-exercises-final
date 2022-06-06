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
	
	
	
	//SQL文
	private static final String SQL_SELECT_ALL = "SELECT p.id,p.product_id, p.name, p.price, c.name AS category, p.description FROM products p INNER JOIN categories c ON p.category_id = c.id ORDER BY p.product_id";
	private static final String SQL_SELECT = "SELECT p.id, p.product_id, p.name, p.price, c.name AS category, p.description FROM products p INNER JOIN categories c ON p.category_id = c.id WHERE ";
	private static final String SQL_SELECT_ID ="SELECT p.id,p.product_id, p.name, p.price, c.name AS category, p.description FROM products p INNER JOIN categories c ON p.category_id = c.id WHERE p.id = :id ORDER BY p.product_id";
	private static final String ORDER = "ORDER BY p.product_id;";
    private static final String INSERT = "INSERT INTO products (product_id, category_id, name, price, description) VALUES (:product_id, :category_id, :name, :price, :description);";
    private static final String DELETE = "DELETE FROM products WHERE id = :id";
    
	
	List<Product> list = null;
	
	//全件検索
	public List<Product> findAll(){
		String sql = SQL_SELECT_ALL;
		
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Product>(Product.class));
	}
	
	//部分一致検索
	public List<Product> find(String searchWord) {
		
    	String WHERE = ("(p.name || c.name LIKE '%' || :searchWord || '%')");
        MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("searchWord", searchWord);

    	String SQL_FIND = SQL_SELECT + WHERE + ORDER;

    	return jdbcTemplate.query(SQL_FIND,param, new BeanPropertyRowMapper<Product>(Product.class));
		
	}
	
	
	public Product findById(Integer id){
		String sql = SQL_SELECT_ID;
		MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("id", id);
    	
    	list = jdbcTemplate.query(sql, param,new BeanPropertyRowMapper<Product>(Product.class));
        return list.isEmpty() ? null : list.get(0);
	}
	
	
	
	
	//登録
	public String register(Product p){
		
        MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("product_id", p.getProduct_id());
    	param.addValue("category_id", p.getCategory_id());
    	param.addValue("name", p.getName());
    	param.addValue("price", p.getPrice());
    	param.addValue("description", p.getDescription());
    	
    	jdbcTemplate.update(INSERT,param);
    	
    	return "登録に成功しました。";
    	
	}
	
	//削除
	public String delete(Integer i) {
		System.out.println("これはDaoの値" + i + "です");
        MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("id", i);

    	jdbcTemplate.update(DELETE,param);
    	
 
    	return "削除に成功しました";
	}
	
	//更新前に商品IDが重複していないか調べるメソッド
	public Product check(Integer id, Integer product_id) {
		String sql = "SELECT * FROM products WHERE id !=:id AND product_id = :product_id;";
		MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("id", id);
    	param.addValue("product_id", product_id);
    	
    	list = jdbcTemplate.query(sql, param,new BeanPropertyRowMapper<Product>(Product.class));
        return list.isEmpty() ? null : list.get(0);
		
	}
	
	//更新
	public String update(Product updateProduct) {
		String sql = "UPDATE products SET product_id = :product_id, category_id = :category_id, name = :name, price = :price WHERE id = :id ";
		MapSqlParameterSource param = new MapSqlParameterSource();

    	param.addValue("product_id", updateProduct.getProduct_id());
    	param.addValue("category_id", updateProduct.getCategory_id());
    	param.addValue("name", updateProduct.getName());
    	param.addValue("price", updateProduct.getPrice());
    	param.addValue("id", updateProduct.getId());
    	
		System.out.println(jdbcTemplate.update(sql,param));
		
		return "更新しました。";
	}
	
}
