package org.configuration.spring.web.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.configuration.spring.web.model.Category;
import org.configuration.spring.web.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductoDaoImplJdbc  implements ProductoDao {
	
	final static Logger logger = Logger.getLogger(ProductoDaoImplJdbc.class);

	@Autowired
	private DataSource dataSource;	
	
	
	@Override
	public List<Product> findAllProducts() {

		String sql = "SELECT id_product, p.code, description, p.name, price , c.name AS nameCat "
				+ " FROM product p INNER JOIN category c ON p.id_category = c.id_category";
		
		Product product = null;
		Connection conn = null;
		CallableStatement cst = null;
		ResultSet rs = null;
		List<Product> lstProd = null;
		
		try {
			logger.info("Busca todos los productos de la bd usando JDBC");	
			conn = dataSource.getConnection();
			cst = conn.prepareCall(sql);
			cst.execute();
			rs = cst.getResultSet();	
			
			lstProd = new ArrayList<Product>();
			
			  while(rs.next()){ 
				  product = new Product();	
				  product.setId_product(rs.getInt("id_product"));
				  product.setCode(rs.getString("code"));
				  product.setName(rs.getString("name"));
				  product.setDescription(rs.getString("description"));
				  product.setPrice(rs.getBigDecimal("price"));

				  lstProd.add(product);			      
	            }
			  
			rs.close();
			cst.close();
			return lstProd;
			
		} catch (SQLException e) {
			logger.error("Error al recuperar los productos de la bd " + e.getMessage());
			throw new RuntimeException(e);
		} finally {	
			cerrarConexion(cst,rs,conn);
		}	
		
	}
	
	private void cerrarConexion(CallableStatement cst, ResultSet rs,Connection conn ) {
		try{ if(cst!=null)cst.close(); } catch(Exception e){e.getMessage();}
		try{ if(rs!=null)rs.close(); } catch(Exception e){e.getMessage();}
		try{ if(conn!=null)conn.close(); } catch(Exception e){e.getMessage();}
		
	}



}
