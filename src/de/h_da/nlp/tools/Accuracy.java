package de.h_da.nlp.tools;
import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Accuracy {

	/**
	 * @param args
	 */
	
	Collection <CombinedLine> CLList = new ArrayList <CombinedLine>();
	Collection <String>errors = new ArrayList();
	float numOfTokens,numOfErrors,overallTaggingAcc,taggingAccForKnownWords,
		numberOfUnknownWords,unknownWordRatio,numberOfErrorsForUnknownWords,
			taggingAccForUnknownWords;
	
	
	
	public static void main(String[] args) {

		assert args.length == 1 : "The programme assumes ONE paramater.";
		
		Accuracy acc = new Accuracy(args[0]);

	}
	
	public Accuracy(String pathOfTaggingResult)
	{
		scanFile(pathOfTaggingResult);
		calculateRates();
		printRates();
	}
	private void handleLine(String combLine)
	{
		String[] splittedLine = combLine.split("[\t\f]");
		String word = splittedLine[0];
		String corTag = splittedLine[1];
		String taggerTag = splittedLine[3];
		boolean UNK = false;
		CombinedLine cl = null;
		
		if (splittedLine.length > 4 && splittedLine[4].equals("<UNK>"))
			UNK = true;
		
		cl = new CombinedLine (word, corTag, taggerTag, UNK);
		
		this.numOfTokens++;
		
		if (!cl.corTag.equals(cl.taggertag))
		{
			String errMessage = "Compound: " + cl.word + " correct: "+cl.corTag +" determined: "+ cl.taggertag;
			errors.add(errMessage);
			this.numOfErrors++;
		}
		
		if (cl.UNK)
		{	
			this.numberOfUnknownWords++;
			if (!cl.corTag.equals(cl.taggertag))
				{
					this.numberOfErrorsForUnknownWords++;
				}
		}
		
		CLList.add(cl);
	}
	
	public void scanFile(String filePath)
	{
		String line;
		File inputFile;
		FileReader fr;
		BufferedReader br;
		try {
			inputFile = new File(filePath);
			fr = new FileReader(inputFile);
			br = new BufferedReader(fr);
			line = br.readLine();
			while (line != null ) {
				
				//ignoring empty lines
				if (line.length()>1&&!line.startsWith("#"))
					this.handleLine(line);
				line = br.readLine();
			}
			br.close();
		} catch (ArrayIndexOutOfBoundsException aioobe) {
			System.out.println(aioobe);
		} catch (FileNotFoundException fnfe) {
			System.out.println(fnfe);
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}
	
	private void calculateRates()
	{
		//number of correct tags = numberOftokens-numberOfErrors
		this.overallTaggingAcc = ((this.numOfTokens-this.numOfErrors)/this.numOfTokens)*100;
	
		float numberOfKnownWords= this.numOfTokens-this.numberOfUnknownWords;
		float numberOfErrorsForKnownWords = this.numOfErrors - this.numberOfErrorsForUnknownWords;
		this.taggingAccForKnownWords = ((numberOfKnownWords - numberOfErrorsForKnownWords)/numberOfKnownWords)*100;
		
		this.taggingAccForUnknownWords = ((this.numberOfUnknownWords - this.numberOfErrorsForUnknownWords)/this.numberOfUnknownWords)*100;
		
		this.unknownWordRatio = (this.numberOfUnknownWords/this.numOfTokens)*100;
	}
	private void printRates() {
		
		System.out.println("\nTest results:");
		System.out.println("\tNumber of compounds: " +this.numOfTokens);
		System.out.println("\tNumber of incorrect relationships: " +this.numOfErrors);
	//	System.out.println("Overall tagging accuracy: " +this.overallTaggingAcc+"%");
		System.out.println("\tAccuracy of compound interperting: " +this.taggingAccForKnownWords+"%");
	//	System.out.println("Number of unknown words: " +this.numberOfUnknownWords);
	//	System.out.println("Unknown word ratio: " +this.unknownWordRatio+"%");
	//	System.out.println("Number of errors for unknown words: " +this.numberOfErrorsForUnknownWords);
	//	System.out.println("Tagging accuracy for unknown words: " +this.taggingAccForUnknownWords+"%");
		System.out.println("\nFound errors: ");
		
		Iterator iter = errors.iterator();
		while (iter.hasNext())
		{
			String errMessage = (String) iter.next();
			System.out.println("\t"+errMessage);
		}
	}

	public class CombinedLine {

		String word;
		String corTag;
		String taggertag;
		
		boolean UNK;

		public CombinedLine(String word, String corTag, String taggertag,
				boolean unk) {
			 
			this.word = word;
			this.corTag = corTag;
			this.taggertag = taggertag;
			UNK = unk;
		}
		
	}
}
