package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankingProjectJpaApplication {

	public static void main(String[] args) {
//		String password=null;
//		try {
//			Resource resource.getInputStream()("classpath:static/pass.txt")).nextLine();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Properties prop = System.getProperties();
//		prop.setProperty("spring.datasource.password", password);
		SpringApplication.run(BankingProjectJpaApplication.class, args);
	}

}
