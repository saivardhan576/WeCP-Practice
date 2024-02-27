package com.springsecurity.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.springsecurity.security.Service.ProductService;

@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext= SpringApplication.run(SecurityApplication.class, args);
		ProductService service=applicationContext.getBean(ProductService.class);
		service.loadProductsFromDB();
	}

}
