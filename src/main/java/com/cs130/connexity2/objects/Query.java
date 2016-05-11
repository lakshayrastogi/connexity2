package com.cs130.connexity2.objects;

import com.cs130.connexity2.util.Globals;

public class Query {
	private Globals.SearchType queryType;

	public Globals.SearchType getQueryType() {
		return queryType;
	}

	public void setQueryType(Globals.SearchType queryType) {
		this.queryType = queryType;
	}
}
