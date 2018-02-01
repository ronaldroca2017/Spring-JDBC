package org.configuration.spring.web.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.configuration.spring.web.dao.ProductoDao;
import org.configuration.spring.web.dao.ProductoDaoImplJdbc;
import org.configuration.spring.web.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService {

	
	final static Logger logger = Logger.getLogger(ProductoServiceImpl.class);

	@Autowired
	ProductoDao productoDao;
	

	@Override
	public List<Product> findAllProducts() {
		return productoDao.findAllProducts();
		
	}


	@Override
	public void saveProduct(Product p) {
		productoDao.saveProduct(p);
		
	}


	@Override
	public Product getProduct(int idProd) {
		return productoDao.getProduct(idProd);
	}


}
