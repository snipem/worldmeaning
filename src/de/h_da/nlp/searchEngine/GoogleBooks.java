package de.h_da.nlp.searchEngine;


public class GoogleBooks extends Google {
	
	public int query (String queryExpression)
	{
		return super.query("\"" + queryExpression + "\"" + " site:books.google.com");
	}

}
