package com.cs130.connexity2;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cs130.connexity2.objects.SearchResult;
import com.cs130.connexity2.twitter.TwitterSearchClient;
import com.cs130.connexity2.util.Globals;

@SpringBootApplication
public class Connexity2Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Connexity2Application.class, args);
	}
	
}
