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
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.h_da.nlp.CompoundInterpreter;

import de.h_da.nlp.type.relationship.Relationship;


public class AccuracyHelper {

	/**
	 * @param args
	 */
	
	CompoundInterpreter ci = new CompoundInterpreter();
	Collection outPutLines = new ArrayList();
	
	HashMap goldFilehm = new HashMap<String, String>();
	
	public AccuracyHelper(String goldFile, String interperterResultFile , String accuracyInputFile)
	{
		System.out.println("Accuracy helper\n");
		System.out.println("\tScanning GoldStandard file:"+goldFile+"\n");
		scanGoldFile(goldFile);
		System.out.println("\tScanning Interperter result file:"+interperterResultFile+"\n");
		scanResultFile(interperterResultFile);
		System.out.println("\tWriting in Accuracy input file:"+accuracyInputFile+"\n");
		printResults(accuracyInputFile);
	}
	
	
	public void scanResultFile(String filePath)
	{
		String compound;
		File inputFile;
		FileReader fr;
		BufferedReader br;
		try {
			inputFile = new File(filePath);
			fr = new FileReader(inputFile);
			br = new BufferedReader(fr);
			String line = br.readLine();
			while (line != null ) {
				
				//ignoring empty lines
				if (line.length()>1&&!line.startsWith("#"))
				{
					//handle line
					String[] splittedLine = line.split("\t");
					CombinedLine cl = new CombinedLine(splittedLine[0], splittedLine[1]);
					this.outPutLines.add(cl);
				}

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
 
	public void scanGoldFile(String filePath)
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
				{
					//handle line
					String[] lineEntries = line.split("\t");
					this.goldFilehm.put(lineEntries[0], lineEntries[1]);
				}

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
	private void printResults(String outputFile) {
		
		File output;
		FileWriter fw;
		BufferedWriter bw;
		String corRel = null;
		try {
			output = new File(outputFile);
			fw = new FileWriter(output);
			bw = new BufferedWriter(fw);
			
			bw.write("# Compound");
			bw.write("\t");
			bw.write("Cor_Rel");
			bw.write("\t");
			bw.write("Compound");
			bw.write("\t");
			bw.write("Found_Rel");
			bw.newLine(); 
			
		    Iterator iter = this.outPutLines.iterator();
			while (iter.hasNext()) 
			{
				CombinedLine cl = (CombinedLine) iter.next();
				String compound = cl.getCompound();
				corRel = (String) this.goldFilehm.get(compound);
				if (corRel == null)
				{
					throw new RuntimeException ("GoldCorpus does not contain the compound :"+compound);
				}
				//write in file
				bw.write(compound);
				bw.write("\t");
				bw.write(corRel.toString());
				bw.write("\t");
				bw.write(compound);
				bw.write("\t");
				bw.write(cl.getRel());
				
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
		String rel = null;
 
	
		public CombinedLine(String compound, String rel) {
			 
			this.compound = compound;
			this.rel = rel;
			 
		}

		public String getCompound() {
			return compound;
		}

		public String getRel() {
			return rel;
		}
	}
}

