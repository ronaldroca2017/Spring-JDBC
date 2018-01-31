package org.configuration.spring.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Product> lstProd = null;
		
		try {
			logger.info("Busca todos los productos de la bd usando JDBC");	
			conn = dataSource.getConnection();
			ps = conn.prepareCall(sql);
			ps.execute();
			rs = ps.getResultSet();	
			
			lstProd = new ArrayList<Product>();
			
			  while(rs.next()){ 
				  product = new Product();	
				  product.setId_product(rs.getInt("id_product"));
				  product.setCode(rs.getString("code"));
				  product.setName(rs.getString("name"));
				  product.setDescription(rs.getString("description"));
				  product.setPrice(rs.getBigDecimal("price"));
				  product.setCategory(new Category(rs.getString("nameCat")));

				  lstProd.add(product);			      
	            }
			  
			rs.close();
			ps.close();
			return lstProd;
			
		} catch (SQLException e) {
			logger.error("Error al recuperar los productos de la bd " + e.getMessage());
			throw new RuntimeException(e);
		} finally {	
			cerrarConexion(ps,rs,conn);
		}	
		
	}
	
	private void cerrarConexion(PreparedStatement ps, ResultSet rs,Connection conn ) {
		try{ if(ps!=null)ps.close(); } catch(Exception e){e.getMessage();}
		try{ if(rs!=null)rs.close(); } catch(Exception e){e.getMessage();}
		try{ if(conn!=null)conn.close(); } catch(Exception e){e.getMessage();}
		
	}

	@Override
	public void saveProduct(Product p) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		String INSERT_SQL = "INSERT INTO PRODUCT(name,"
	            + "code,price,description,id_Category) "
	            + "VALUES(?,?,?,?,?)";
	
		 try {
			 logger.info("Registrando el producto en la bd usando JDBC");	
				conn = dataSource.getConnection();
	            ps = conn.prepareStatement(INSERT_SQL);
	            ps.setString(1, p.getName());
	            ps.setString(2, p.getCode());
	            ps.setBigDecimal(3, p.getPrice());
	            ps.setString(4, p.getDescription());
	            ps.setInt(5, p.getCategory().getId_Category());
	            
	            ps.executeUpdate();  
	            
	            ps.close();
	            conn.close();
	            
	        } catch (Exception e) {
	        	logger.error("Error al Registrar el producto en la bd " + e.getMessage());
				throw new RuntimeException(e);
	        }finally{
	        	try {
	        		ps.close();
					conn.close();		
				} catch (SQLException e) {
					e.printStackTrace();
				}
	        }
		
	}



}
