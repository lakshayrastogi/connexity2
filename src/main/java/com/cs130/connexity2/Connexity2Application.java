package com.cs130.connexity2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cs130.connexity2.objects.SearchResultJDBCTemplate;
import com.cs130.connexity2.util.Globals;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Connexity2Application {
	   
	public static void main(String[] args) {
		DriverManagerDataSource dm = new DriverManagerDataSource();
		dm.setDriverClassName(Globals.JDBC_DRIVER);
		dm.setUrl(Globals.HOST_URL);
		dm.setUsername(Globals.USER);
		dm.setPassword(Globals.PASS);
		JdbcTemplate jt = new JdbcTemplate(dm); 
		String sql = "CREATE DATABASE CONNEXITY";
		try{
			jt.execute(sql);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			System.out.println("DONE Creating Database!");
		}
		
		SearchResultJDBCTemplate searchJT = new SearchResultJDBCTemplate();
		searchJT.createTable();
		   
		SpringApplication.run(Connexity2Application.class, args);
	}
	
}
