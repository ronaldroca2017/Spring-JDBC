package org.configuration.spring.web.controller;


import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.configuration.spring.web.model.Category;
import org.configuration.spring.web.model.Product;
import org.configuration.spring.web.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductoController {

	final static Logger logger = Logger.getLogger(ProductoController.class);
	
	@Autowired
	ProductoService productoService;
	
	@RequestMapping("/listaProducto.htm")
	public ModelAndView listarProducto(){
		logger.info("ProductoController -->  listarProducto() ");
		
		List<Product>  lstProduct = productoService.findAllProducts();
		
		ModelAndView mav = new ModelAndView();	
		mav.addObject("lstProduct", lstProduct);
		mav.setViewName("producto_listado");
		
		return mav;
	}
	
	@RequestMapping("/loadRegistrarProducto.htm")
	public ModelAndView saveProducto(HttpServletRequest request){		
		return new ModelAndView("producto_register");
	}
	
	@RequestMapping("/registrarProducto.htm")
	public String registrarProducto(HttpServletRequest request){
		logger.info("ProductoController -->  registrarProducto() ");
		
		String code = request.getParameter("code");
		String desc = request.getParameter("descripcion");
		String name = request.getParameter("name");
		String precio = request.getParameter("precio");
		String cat = request.getParameter("categoriaId");
		
		logger.info(code + " - " + desc + " - " + name + " - " + precio+ " - " + cat);
		
		Product p = new Product();
		p.setName(name);
		p.setCode(code);
		p.setDescription(desc);
		p.setPrice(new BigDecimal(precio));
		Category categ = new Category();						
		categ.setId_Category(Integer.parseInt(cat));
		p.setCategory(categ);
		
		productoService.saveProduct(p);

		return "redirect:/listaProducto.htm";
	}
	
	@RequestMapping("/loadProducto.htm")
	public ModelAndView loadProducto(@RequestParam("id") Integer idProduct){		
		
		Product prod =  productoService.getProduct(idProduct);
		ModelAndView mav = new ModelAndView();	
		mav.addObject("product", prod);
		mav.setViewName("producto_update");
		return mav;
	}
	
	
	@RequestMapping("/updateProducto.htm")
	public String updateProducto(@ModelAttribute("Product") Product p){		
		
		logger.info(p.toString());
		productoService.updateProduct(p);
	
		return "redirect:/listaProducto.htm";
	}

	
}
