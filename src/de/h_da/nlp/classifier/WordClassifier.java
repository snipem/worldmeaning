package de.h_da.nlp.classifier;

import de.h_da.nlp.type.MorphemeClass;

public abstract class WordClassifier{
	public abstract MorphemeClass getWordClass(String word);
}
