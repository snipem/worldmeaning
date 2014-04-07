package de.h_da.nlp.classifier;

import de.h_da.nlp.type.MorphemeClass;

public class DummyWordClassifier extends WordClassifier {

	@Override
	public MorphemeClass getWordClass(String word) {
		return MorphemeClass.NOUN;
	}
}
