package de.h_da.nlp.type.relationship;

import java.util.LinkedList;
import java.util.List;

import de.h_da.nlp.exceptions.NoQueryDefinedException;

public abstract class Relationship {

	String meaning = null;
	String modifier = null;
	String head = null;
	List <String> query;
	int numOfFoundEntries = 0;
	double weighting = 1.0;
	
	public int getNumOfFoundEntries() {
		return numOfFoundEntries;
	}
	public Relationship()
	{
		this.query = new LinkedList<String>();
	}

	public List<String> getQuery() throws NoQueryDefinedException
	{
		if (query==null)
			throw new NoQueryDefinedException();
		
		return query;
	}
	public String getMeaning()
	{
		return meaning;
	}
	public void setFoundEntries(int foundEntries)
	{
		this.numOfFoundEntries = foundEntries;
	}
	public Double getWeighting() 
	{
		return weighting;
	}
	public void setWeighting(double setWeight) {
		
		weighting = setWeight;
	}
	
	public String toString()
	{
	
		return this.getClass().getSimpleName();}
}
