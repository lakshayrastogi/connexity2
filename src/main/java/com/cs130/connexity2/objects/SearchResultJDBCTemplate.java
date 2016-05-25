package com.cs130.connexity2.objects;

import com.cs130.connexity2.util.Globals;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import com.google.gson.Gson;

public class SearchResultJDBCTemplate {
	private DriverManagerDataSource dmDataSource;
	private JdbcTemplate jt;
	
	public SearchResultJDBCTemplate() {
		dmDataSource = new DriverManagerDataSource();
		dmDataSource.setDriverClassName(Globals.JDBC_DRIVER);
		dmDataSource.setUrl(Globals.DB_URL);
		dmDataSource.setUsername(Globals.USER);
		dmDataSource.setPassword(Globals.PASS);
		jt = new JdbcTemplate(dmDataSource);
	}
	
	public void createTable() {
		String sql = "create table SearchResults ("
				+ "serialNum INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,"
				+ "merchantId BIGINT,"
				+ "id BIGINT,"
				+ "title VARCHAR(1000),"
				+ "brandName VARCHAR(50),"
				+ "description VARCHAR(10000),"
				+ "url VARCHAR(1000),"
				+ "images VARCHAR(10000),"
				+ "price DOUBLE,"
				+ "priceStr VARCHAR(100),"
				+ "merchantName VARCHAR(100),"
				+ "merchantLogoUrl VARCHAR(1000)"
				+ ")";
		try{
			jt.execute(sql);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			System.out.println("DONE Creating Table!");
		}
	}
	
	public void insertRecord(SearchResult sr) {
		String sql = "insert into SearchResults (merchantId, id, title, brandName, description, url, images, price, priceStr, merchantName, merchantLogoUrl) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		// Convert List<String> to JSON String
		Gson gson = new Gson();
		String imageInString = gson.toJson(sr.getImages());
		
		try{
			jt.update(sql, sr.getMerchantId(), sr.getId(), sr.getTitle(), sr.getBrandName(), sr.getDescription(), sr.getUrl(),
					imageInString, sr.getPrice(), sr.getPriceStr(), sr.getMerchantName(), sr.getMerchantLogoUrl());
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			System.out.println("DONE Inserting Record!");
		}
	}
	
	public List<SearchResult> getSearchResults(String clause) {
	      String sql = "select * from SearchResults " + clause;
	      List<SearchResult> searchResults = new ArrayList<>();
	      try{
		      searchResults = jt.query(sql, new SearchResultsMapper());	    	  
	      }catch(Exception e){
	    	  System.out.println(e.getMessage());
	      }finally{
	    	  System.out.println("DONE Fetching Records!");
	      }
	      return searchResults;
	}
	
	public void deleteAllRows() {
	      String sql = "truncate SearchResults";
	      try{
	    	  jt.execute(sql);
	      }catch(Exception e){
	    	  System.out.println(e.getMessage());
	      }finally{
	    	  System.out.println("DONE Deleting All Records!");
	      }
	}
}
