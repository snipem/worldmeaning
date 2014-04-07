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

import java.util.logging.Level;
import java.util.logging.Logger;

import de.h_da.nlp.classifier.DummyWordClassifier;
import de.h_da.nlp.classifier.WiktionaryWordClassifier;
import de.h_da.nlp.classifier.WordClassifier;
import de.h_da.nlp.exceptions.NoCompoundsExtractedException;
import de.h_da.nlp.tools.Accuracy;
import de.h_da.nlp.tools.AccuracyHelper;
import de.h_da.nlp.tools.CompoundSeparator;
import de.h_da.nlp.tools.TestFileEvaluator;
import de.h_da.nlp.type.Compound;
import de.h_da.nlp.type.MorphemeClass;
import de.h_da.nlp.type.Morpheme;
import de.h_da.nlp.type.relationship.Relationship;

public class CompoundInterpreter {

	/**
	 * @param word to be evaluated
	 * @throws Exception 
	 */
	Logger logger = Logger.getLogger("WorldMeaning");
	
	public Relationship analyseCompound(String word, boolean cache)  
	{
		System.out.println("Compound: " + word);
		
		CompoundSeparator mySeperator = null;
		try {
			mySeperator = new CompoundSeparator(word);
		} catch (NoCompoundsExtractedException e) {
			System.err.println("No compounds found, exiting");
			System.exit(1);
		}
		
		WordClassifier wc = new WiktionaryWordClassifier();
		//wc = new DummyWordClassifier();
		
		String m1 = mySeperator.getMorpheme(1);
		String m2 = mySeperator.getMorpheme(2);
		MorphemeClass wc1 = wc.getWordClass(m1);
		MorphemeClass wc2 = wc.getWordClass(m2);
		
		//building 2 morphemes
		Morpheme morpheme1 = new Morpheme(m1,wc1);
		Morpheme morpheme2 = new Morpheme(m2,wc2);
		
		System.out.println("Morphemes: " + morpheme1.morpheme + " (" + morpheme1.wc.toString() + ") - " + morpheme2.morpheme + " (" + morpheme2.wc.toString() + ")");
		
		//instantiating compound
		Compound compound = new Compound(morpheme1, morpheme2);
		
		ResponseEvaluator myMeaning = new ResponseEvaluator(compound);
		
		myMeaning.evaluate(cache);
		Relationship unWeithedRel = myMeaning.getBestMatch();
		if (unWeithedRel == null)
		{
			//no relationship is assigned to compound
			System.err.println("No relationship is assigned to the compound");
			return null;		
		}
		logger.info("\nResult normal  : " + unWeithedRel.getMeaning());
		Relationship weithedRel = myMeaning.getBestWeightedMatch();
		if (unWeithedRel == null)
		{
			System.err.println("No relationship is assigned to the compound");
			return null;
		}
		logger.info("\nResult weighted: " + weithedRel.getMeaning());
		
		//Return weighted relation, forget the unweighted
		return weithedRel;
	}
	

	 public void accuracyTester()
	{
		 String testCompounds="./TestCompounds.txt";
			String goldStandard = "./GoldStandard.txt";
			String interperterResultFile="./Results.txt";
			String accuracyInputFile = "./AccuracyInput.txt";
			
			//finding Relationships for compounds included in TestCompounds.txt
			//output--> Results.txt
			TestFileEvaluator tfe = new TestFileEvaluator(testCompounds, interperterResultFile);
			
			//preparing accuracy input file: AccuracyInput.txt
			AccuracyHelper ah = new AccuracyHelper(goldStandard, interperterResultFile, accuracyInputFile);
		  
			//Calculating accuracy rates
			Accuracy ac = new Accuracy(accuracyInputFile);
	}
	 public static void main(String[] args) {

			Logger logger = Logger.getLogger("WorldMeaning");
			logger.setLevel(Level.ALL);
		 
			CompoundInterpreter ci = new CompoundInterpreter();	
			ci.accuracyTester();
		}
}
