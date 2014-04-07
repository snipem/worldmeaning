package de.h_da.nlp.searchEngine;

import java.util.Random;

public class Dummy extends Google {

	public int query (String query)
	{
		//Forget string and return random value
		return (new Random()).nextInt(500);
	}
	
}
