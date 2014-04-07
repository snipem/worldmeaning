package de.h_da.nlp.tools;
import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.h_da.nlp.CompoundInterpreter;

import de.h_da.nlp.type.relationship.Relationship;


public class TestFileEvaluator {

	/**
	 * @param args
	 */
	
	CompoundInterpreter ci = new CompoundInterpreter();
	Collection outPutLines = new ArrayList();
	
	
	public TestFileEvaluator(String inputFile, String outputFile)
	{
		System.out.println("TestFileEvaluator\n");
		scanFile(inputFile);
		System.out.println("\tWriting results to "+ outputFile +"\n");
		printResults(outputFile);
	}
	
	
	public void scanFile(String filePath)
	{
		String compound;
		File inputFile;
		FileReader fr;
		BufferedReader br;
		try {
			inputFile = new File(filePath);
			fr = new FileReader(inputFile);
			br = new BufferedReader(fr);
			compound = br.readLine();
			while (compound != null ) {
				
				//ignoring empty lines
				if (compound.length()>1&&!compound.startsWith("#"))
				{
					
					//handle line
					String [] line = compound.split("\t");
					compound = line[0];
					Relationship rel = this.ci.analyseCompound(compound,true); 
					
					CombinedLine cl = new CombinedLine(compound, rel);
					this.outPutLines.add(cl);
				}

				compound = br.readLine();
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
 
	 
	private void printResults(String outputFile) {
		
		File output;
		FileWriter fw;
		BufferedWriter bw;
		try {
			output = new File(outputFile);
			fw = new FileWriter(output);
			bw = new BufferedWriter(fw);
		    Iterator iter = this.outPutLines.iterator();
			while (iter.hasNext()) 
			{
				CombinedLine cl = (CombinedLine) iter.next();
				bw.write(cl.getCompound());
				bw.write("\t");
				Relationship rel = cl.getRel();
				if (rel!= null)
					bw.write(rel.toString());
				else 
					bw.write("-");
				
				if (iter.hasNext())
					bw.newLine();
			}
			 
			bw.close(); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public class CombinedLine {

		String compound;
		Relationship rel = null;
 
	
		public CombinedLine(String compound, Relationship rel) {
			 
			this.compound = compound;
			this.rel = rel;
			 
		}

		public String getCompound() {
			return compound;
		}

		public Relationship getRel() {
			return rel;
		}
	}
}

