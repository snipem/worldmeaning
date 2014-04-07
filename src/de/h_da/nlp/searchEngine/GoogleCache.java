package de.h_da.nlp.searchEngine;
import java.io.*;
import java.util.logging.Logger;
public class GoogleCache extends Google {

	Logger logger = Logger.getLogger("WorldMeaning");
	public int query (String query)
	{
		boolean found = false;
		FileWriter fstream;
		int results = 0;
		FileReader fr;

		try {

			fr = new FileReader(".cache");

			BufferedReader br = new BufferedReader(fr); 
			String s;

			while((s = br.readLine()) != null) { 

				if (s.startsWith(query))
				{
					results = Integer.parseInt(s.substring(s.indexOf("\t")+1, s.length()));

					if (results > 0)
					{
						logger.info("\tquering <CACHE> with: "+query+"\tfound <CACHE>: "+results);
					}
					found = true;
				}

			}

			fr.close();

		} catch (IOException e1) {
			e1.printStackTrace();
		} 

		// Else ask Google and write to cache
		if (!found)
		{

			try {

				fstream = new FileWriter(".cache", true);

				results = super.query(query);
				BufferedWriter out = new BufferedWriter(fstream);
				out.append(query + "\t" + results + "\n");

				out.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return results;
	}

}
