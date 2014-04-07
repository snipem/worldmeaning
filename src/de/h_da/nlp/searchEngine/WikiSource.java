package de.h_da.nlp.searchEngine;


public class WikiSource extends Google {
	
	public int query (String queryExpression)
	{
		return super.query("\"" + queryExpression + "\"" + " -\"User_talk\" site:en.wikisource.org");
	}

}
