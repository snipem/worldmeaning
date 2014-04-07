package de.h_da.nlp;

/*
Copyright (c) 2010 A. Maraschi, M. Kuech

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

//import de.h_da.nlp.google.Dummy;
import de.h_da.nlp.exceptions.NoQueryDefinedException;
import de.h_da.nlp.searchEngine.*;
import de.h_da.nlp.type.relationship.*;
import de.h_da.nlp.type.Compound;;

public class ResponseEvaluator {

	Compound compound = null;

	Collection<Relationship> meaning = new ArrayList<Relationship>();

	public ResponseEvaluator(Compound compound) {
		this.compound = compound;
	}
	Logger logger = Logger.getLogger("WorldMeaning");
	public void evaluate(boolean cache) {

		//http://books.google.de/books?id=eK8R61s_gwQC&lpg=PA32&dq=compounding%20relationships&ie=ISO-8859-1&pg=PA33#v=onepage&q=compounding%20relationships&f=false

		SearchEngine gq = new Google();
		
		if (cache == true)
			gq = new GoogleCache();
		//Dummy Search for offline usage or testing. Returning random numbers.
		//gq = new Dummy();

		Iterator<Relationship> iter = compound.getPossibleRelationships().iterator();


		// Do Queries
		while (iter.hasNext())
		{
			Relationship rel = (Relationship) iter.next();
			logger.finer("Relationship" + rel.getClass().toString());
			List<String> queries;
			try {
				queries = rel.getQuery();

				Iterator<String> it = queries.iterator();
				int foundEntries = 0;

				while (it.hasNext())
				{
					//System.out.println(foundEntries);
					foundEntries += gq.query(it.next().toString());
				}

				logger.finer("Found sum:"+foundEntries+"\n");
				rel.setFoundEntries(foundEntries);
				meaning.add(rel);


			} catch (NoQueryDefinedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//System.out.println("\nQuerying Google:\n");
		//		Iterator<Relationship> iter1 = meaning.iterator();
		//		while (iter1.hasNext()) {
		//			Relationship pr = (Relationship) iter1.next();
		//			System.out.println(pr.getMeaning() + "; Results " + (Integer) pr.getNumOfFoundEntries());
		//		}
	}

	public Relationship getBestMatch()
	{
		
		
		Relationship bestMatch = null;
		Integer bestNumberOfMatches = 0;

		Iterator<Relationship> iter = meaning.iterator();

		//Sort by best match
		while (iter.hasNext()) {
			Relationship pr = (Relationship) iter.next();
			Integer numberOfMatches = (Integer) pr.getNumOfFoundEntries();

			if (numberOfMatches > bestNumberOfMatches)
			{
				bestNumberOfMatches = numberOfMatches;
				bestMatch = pr;
			}
		}
		
		
		return bestMatch;
	}

	public Relationship getBestWeightedMatch()
	{
		Relationship bestMatch = null;
		Double bestNumberOfMatches = 0.0;

		Iterator<Relationship> iter = meaning.iterator();

		//Sort by best weighted match
		while (iter.hasNext()) {
			Relationship pr = (Relationship) iter.next();
			Integer numberOfMatches = (Integer) pr.getNumOfFoundEntries();
			Double weighting = (Double) pr.getWeighting();


			if ((numberOfMatches * weighting) > bestNumberOfMatches)
			{
				bestNumberOfMatches = (numberOfMatches * weighting);
				bestMatch = pr;
			}
		}
		return bestMatch;
	}

	public Compound getCompound()
	{
		return compound;
	}

}
