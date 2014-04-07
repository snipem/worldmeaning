package de.h_da.nlp.classifier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import de.h_da.nlp.type.MorphemeClass;

public class WiktionaryWordClassifier extends WordClassifier {
	private final String HTTP_REFERER = "http://worldmeaning.google.code/";
	@Override
	public MorphemeClass getWordClass(String word) {

		// Convert spaces to +, etc. to make a valid URL
		try {
			word = URLEncoder.encode(word, "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}


		String u = "http://en.wiktionary.org/w/api.php?action=query&prop=revisions&titles="+ word +"&rvprop=content&format=xml";

		URL url = null;
		try {
			url = new URL(u);
		} catch (MalformedURLException e) {
		}

		URLConnection connection = null;
		try {
			connection = url.openConnection();
		} catch (IOException e) {
		}
		connection.addRequestProperty("Referer", HTTP_REFERER);

		// Get the JSON response
		String line;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
		} catch (IOException e) {
		}


		try {
			while ((line = reader.readLine()) != null) {

				if (line.startsWith("==="))
				{
					String tempWord = line.replaceAll("==*", "");
					if (tempWord.matches("Adjective"))
					{
						return MorphemeClass.ADJECTIVE;
					}
					else if (tempWord.matches("Noun"))
					{
						return MorphemeClass.NOUN;
					}
					else if (tempWord.matches("Adverb"))
					{
						return MorphemeClass.ADVERB;
					}
					else if (tempWord.matches("Verb"))
					{
						return MorphemeClass.VERB;
					}
					else if (tempWord.matches("Preposition"))
					{
						return MorphemeClass.PREPOSITION;
					}
					else if (tempWord.matches("Article"))
					{
						return MorphemeClass.ARTICLE;
					}

				}

			}
		} catch (IOException e) {
		}

		// If nothing is returned so far
		return MorphemeClass.NOUN;

	}

}
