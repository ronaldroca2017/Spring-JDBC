package org.configuration.spring.web.dao;

import java.util.List;

import org.configuration.spring.web.model.Product;

public interface ProductoDao {
	
	List<Product> findAllProducts();
	void  saveProduct(Product p);
	Product getProduct(int idProd);
	
}
