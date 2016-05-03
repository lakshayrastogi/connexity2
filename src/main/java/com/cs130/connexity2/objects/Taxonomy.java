package com.cs130.connexity2.objects;

import java.util.ArrayList;
import java.util.List;

public class Taxonomy {
	private List<Category> categories = new ArrayList<Category>();
	
	public Taxonomy() {
		// TODO Auto-generated constructor stub
	}

	public Taxonomy(List<Category> categories) {
		this.categories = categories;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	
}
