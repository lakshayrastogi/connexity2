package com.cs130.connexity2.objects;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SearchResultsMapper implements RowMapper<SearchResult> {
	public SearchResult mapRow(ResultSet rs, int rowNum) throws SQLException {
	      SearchResult searchResult = new SearchResult();
	      searchResult.setMerchantId(rs.getLong("merchantId"));
	      searchResult.setTitle(rs.getString("title"));
	      searchResult.setBrandName(rs.getString("brandName"));
	      searchResult.setDescription(rs.getString("description"));
	      searchResult.setUrl(rs.getString("url"));
	      
	      // Convert JSON string to List<String>
	      Gson gson = new Gson();
	      List<String> list = gson.fromJson(rs.getString("images"), new TypeToken<List<String>>(){}.getType());
	      searchResult.setImages(list);
	      
	      searchResult.setPrice(rs.getDouble("price"));
	      searchResult.setPriceStr(rs.getString("priceStr"));
	      searchResult.setMerchantName(rs.getString("merchantName"));
	      searchResult.setMerchantLogoUrl(rs.getString("merchantLogoUrl"));

	      return searchResult;
	}
}
