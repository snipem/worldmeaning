package de.h_da.nlp.test;

import de.h_da.nlp.type.MorphemeClass;

public class DesiredMeaning {

	private String compoundedWord;
	private String goalMorpheme1;
	private String goalMorpheme2;
	private MorphemeClass wc1;
	private MorphemeClass wc2;
	private String goalMeaning;
	
	public DesiredMeaning(String compoundedWord, String goalMorpheme1, 
			String goalMorpheme2,
			MorphemeClass wc1, MorphemeClass wc2,  String goalMeaning) {

		this.compoundedWord = compoundedWord;
		this.goalMorpheme1 = goalMorpheme1;
		this.goalMorpheme2 = goalMorpheme2;
		this.wc1 = wc1;
		this.wc2 = wc2;
		this.goalMeaning = goalMeaning;
	
	}

	public String getCompoundedWord() {
		return compoundedWord;
	}

	public void setCompoundedWord(String compoundedWord) {
		this.compoundedWord = compoundedWord;
	}

	public String getGoalMorpheme1() {
		return goalMorpheme1;
	}

	public void setGoalMorpheme1(String goalMorpheme1) {
		this.goalMorpheme1 = goalMorpheme1;
	}

	public String getGoalMorpheme2() {
		return goalMorpheme2;
	}

	public void setGoalMorpheme2(String goalMorpheme2) {
		this.goalMorpheme2 = goalMorpheme2;
	}

	public MorphemeClass getWc1() {
		return wc1;
	}

	public void setWc1(MorphemeClass wc1) {
		this.wc1 = wc1;
	}

	public MorphemeClass getWc2() {
		return wc2;
	}

	public void setWc2(MorphemeClass wc2) {
		this.wc2 = wc2;
	}

	public String getGoalMeaning() {
		return goalMeaning;
	}

	public void setGoalMeaning(String goalMeaning) {
		this.goalMeaning = goalMeaning;
	}

}
