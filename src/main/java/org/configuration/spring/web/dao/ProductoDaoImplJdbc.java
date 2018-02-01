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

			return lstProd;
			
		} catch (SQLException e) {
			logger.error("Error al recuperar los productos de la bd " + e.getMessage());
			throw new RuntimeException(e);
		} finally {	
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}	
		
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

	
	@Override
	public Product getProduct(int idProd) {
		
		Product prod = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
	        try {
	            
	            conn = dataSource.getConnection();
	            String sql = "SELECT id_product, p.code, description, p.name, price , c.name AS nameCat "
	            		+ " FROM product p INNER JOIN category c ON p.id_category = c.id_category WHERE  id_product = ?";
	            ps = conn.prepareStatement(sql);
	            ps.setInt(1, idProd);
	            rs = ps.executeQuery();
	            
	            
	            
	            if(rs.next()){
	            	prod = new Product();	
	            	prod.setId_product(rs.getInt("id_product"));
	            	prod.setCode(rs.getString("code"));
	            	prod.setName(rs.getString("name"));
	            	prod.setDescription(rs.getString("description"));
	            	prod.setPrice(rs.getBigDecimal("price"));
	            	prod.setCategory(new Category(rs.getString("nameCat")));
	          
	            
	            }
	        } catch (Exception e) {
	        	logger.error("Error al obtener el producto en la bd " + e.getMessage());
				throw new RuntimeException(e);
	        }finally{
	        	try {
	        		rs.close();
					ps.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	        }
	        return prod;
	}


	@Override
	public void updateProduct(Product p) {
		Connection conn = null;
		PreparedStatement ps = null;

		
		String UPDATE_SQL = "UPDATE PRODUCT SET "
                 + "code = ?,"
                 + "name=?,"
                 + "description=?,"
                 + "price=?,"
                 + "id_Category = ? "
                 + "WHERE id_product = ?";
	
		 try {
			 logger.info("Actualizando el producto en la bd usando JDBC");	
				conn = dataSource.getConnection();
	            ps = conn.prepareStatement(UPDATE_SQL);
	            ps.setString(1, p.getCode());
	            ps.setString(2, p.getName());
	            ps.setString(3, p.getDescription());
	            ps.setBigDecimal(4, p.getPrice());
	            ps.setInt(5, p.getCategory().getId_Category());
	            ps.setInt(6, p.getId_product());
	           
	            ps.executeUpdate();  
	                        
	        } catch (Exception e) {
	        	logger.error("Error al Actualizar el producto en la bd " + e.getMessage());
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
