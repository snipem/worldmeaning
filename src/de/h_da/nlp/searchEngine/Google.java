package de.h_da.nlp.searchEngine;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.logging.Logger;

import org.json.JSONObject;

public class Google implements SearchEngine{

	private final String HTTP_REFERER = "http://worldmeaning.google.code/";

	Logger logger = Logger.getLogger("WorldMeaning");
	public int query(String query) {

		int numberOfResults = 0;
		try {
			
			logger.info("\tquering google with: "+query);
			// Convert spaces to +, etc. to make a valid URL
			query = URLEncoder.encode(query, "UTF-8");
			
			URL url = new URL(
					"http://ajax.googleapis.com/ajax/services/search/web?start=0&rsz=large&v=1.0&q="
							+ query);
			URLConnection connection = url.openConnection();
			connection.addRequestProperty("Referer", HTTP_REFERER);

			// Get the JSON response
			String line;
			StringBuilder builder = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}

			String response = builder.toString();
			JSONObject json = new JSONObject(response);


			String numberOfResultsString = json.getJSONObject("responseData")
					.getJSONObject("cursor").getString("estimatedResultCount");

			numberOfResults = Integer.valueOf(numberOfResultsString).intValue();
			logger.info("\tfound: "+numberOfResults);
			
		} catch (Exception e) {
			return 0;
		}
		return numberOfResults;
	}

}