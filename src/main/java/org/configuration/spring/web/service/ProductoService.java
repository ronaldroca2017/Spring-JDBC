package org.configuration.spring.web.service;

import java.util.List;

import org.configuration.spring.web.model.Product;

public interface ProductoService {
	
	List<Product> findAllProducts();

}
