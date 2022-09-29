package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

@ComponentScan({"com.example.demo","service"})



public class CrudOperation12Application {

		protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CrudOperation12Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(CrudOperation12Application.class, args);
	}


}
