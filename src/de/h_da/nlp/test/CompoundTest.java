package de.h_da.nlp.test;

import static org.junit.Assert.assertTrue;
import de.h_da.nlp.ResponseEvaluator;
import de.h_da.nlp.classifier.WiktionaryWordClassifier;
import de.h_da.nlp.classifier.WordClassifier;
import de.h_da.nlp.exceptions.NoCompoundsExtractedException;
import de.h_da.nlp.tools.CompoundSeparator;
import de.h_da.nlp.type.Compound;
import de.h_da.nlp.type.Morpheme;
import de.h_da.nlp.type.MorphemeClass;

public class CompoundTest {

	public ResponseEvaluator testerHelper (String word) 
	{
		System.out.println("Compound: " + word);

		CompoundSeparator mySeperator = null;
		try {
			mySeperator = new CompoundSeparator(word);
		} catch (NoCompoundsExtractedException e) {
			System.out.println("No compounds found, exiting");
			System.exit(1);
		}

		WordClassifier wc = new WiktionaryWordClassifier();

		String m1 = mySeperator.getMorpheme(1);
		String m2 = mySeperator.getMorpheme(2);
		MorphemeClass wc1 = wc.getWordClass(m1);
		MorphemeClass wc2 = wc.getWordClass(m2);

		Morpheme morpheme1 = new Morpheme(m1,wc1);
		Morpheme morpheme2 = new Morpheme(m2,wc2);

		Compound compound = new Compound(morpheme1, morpheme2);

		ResponseEvaluator myMeaning = new ResponseEvaluator(compound);

		myMeaning.evaluate(true);

		return myMeaning;
	}

	public void compoundTest(DesiredMeaning desiredMeaning)
	{
		System.out.println("\n------------------------------------------------------\n");
		System.out.println(desiredMeaning.getCompoundedWord());

		// Goal Specification

		String compoundedWord = desiredMeaning.getCompoundedWord();
		String goalMorpheme1 = desiredMeaning.getGoalMorpheme1();
		String goalMorpheme2 = desiredMeaning.getGoalMorpheme2();

		String goalMeaning = desiredMeaning.getGoalMeaning();

		MorphemeClass goalMorphemeClass1 = desiredMeaning.getWc1();
		MorphemeClass goalMorphemeClass2 = desiredMeaning.getWc2();

		// Run Test

		ResponseEvaluator testMeaning =
			testerHelper(compoundedWord);

		Compound testCompound = testMeaning.getCompound();

		Morpheme morpheme1 = testCompound.getMorphemes().get(0);
		Morpheme morpheme2 = testCompound.getMorphemes().get(1);

		assertTrue(morpheme1.morpheme.compareTo(goalMorpheme1) == 0);
		assertTrue(morpheme2.morpheme.compareTo(goalMorpheme2) == 0);

		assertTrue(morpheme1.wc.compareTo(goalMorphemeClass1) == 0);
		assertTrue(morpheme2.wc.compareTo(goalMorphemeClass2) == 0);

		System.out.println(testMeaning.getBestWeightedMatch());

		//TODO: This should test on Object level rather than testing the string value
		assertTrue(testMeaning.getBestWeightedMatch().getMeaning().startsWith(goalMeaning));

	}

}
