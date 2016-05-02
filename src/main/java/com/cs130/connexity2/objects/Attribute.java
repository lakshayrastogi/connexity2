package com.cs130.connexity2.objects;

import java.util.ArrayList;
import java.util.List;

public class Attribute {
	
	private int id;
	private String name;
	private List<AttributeValue> myList = new ArrayList<AttributeValue>();
	
	public Attribute(int id, String name, List<AttributeValue> myList) {
		this.id = id;
		this.name = name;
		this.myList = myList;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<AttributeValue> getMyList() {
		return myList;
	}
	public void setMyList(List<AttributeValue> myList) {
		this.myList = myList;
	}

	
}
