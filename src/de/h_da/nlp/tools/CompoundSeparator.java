package de.h_da.nlp.tools;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

import de.h_da.nlp.exceptions.NoCompoundsExtractedException;

public class CompoundSeparator {
	// Extract GNU Aspell dictionary: http://typethinker.blogspot.com/2008/02/fun-with-aspell-word-lists.html

	private LinkedList<String> dictionary; 
	private String[] morphemes;

	public CompoundSeparator(String word) throws NoCompoundsExtractedException {

		// Dictionary is derived from GNU Aspell
		this.dictionary = new LinkedList<String>();
		this.processWordlist();

		//System.out.println("Dictionary size: " + this.dictionary.size());

		morphemes = new String[2];

		process(word);

	}

	private void process(String word) throws NoCompoundsExtractedException {

		boolean wordsFound = false;

	 

		if (word.contains(" ")) // with white space
		{
			String firstPart = word.substring(0, word.indexOf(" "));
			String lastPart = word.substring(word.indexOf(" ")+1,word.length());

			morphemes[0] = firstPart;
			morphemes[1] = lastPart;
			wordsFound = true;
		}
		else if (word.contains("-")) // with separator
		{
			String firstPart = word.substring(0, word.indexOf("-"));
			String lastPart = word.substring(word.indexOf("-")+1,word.length());

			morphemes[0] = firstPart;
			morphemes[1] = lastPart;
			wordsFound = true;
		}
		else // as a single word
		{
			// Begin with first two chars
			int charStep = word.length()-3;

			while(!wordsFound && charStep <= word.length())
			{
				String firstPart = word.substring(0,charStep);
				String lastPart = word.substring(charStep);

				// Iterates over word and analyzes each part for example d and oorknob. If
				// both are in dictionary we found the compound
				if (dictionary.indexOf(lastPart) != -1)
				{
					if (dictionary.indexOf(firstPart) != -1)
					{
						morphemes[0] = firstPart;
						morphemes[1] = lastPart;
						wordsFound = true;
					}
				}

				charStep--;
			}

		}

		if (!wordsFound)
		{
			throw new NoCompoundsExtractedException();
		}
	}

	private void processWordlist()
	{
		try{
			FileInputStream fstream = new FileInputStream("english.txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;

			while ((strLine = br.readLine()) != null) 	{
				dictionary.add( strLine );
			}
			in.close();
		}catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
	}

	// Get compounds starting with 1
	public String getMorpheme(int i) {

		if (i-1 <= morphemes.length)
			return morphemes[i-1];
		else
			return null;
	}

}
