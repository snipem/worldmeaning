package de.h_da.nlp.type;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import de.h_da.nlp.type.relationship.*;

public class Compound {

	private String compound;
	
	/*
	 * Modifier 	Head 	Compound
		 noun  	    noun 	football
	 */
	private Morpheme modifier;
	private Morpheme head;
	private CompoundClass cc;
	Logger logger = Logger.getLogger("WorldMeaning");
	Collection<Relationship> possibleRelationships = new ArrayList<Relationship>();
	public Collection<Relationship> getPossibleRelationships() {
		return possibleRelationships;
	}

	public Compound(Morpheme modifier, Morpheme head ) {

		this.modifier = modifier;
		this.head = head;

		//Classifying the compound
		
		//classifying noun compounds:  head is a noun
		switch (head.wc)
		{
		case NOUN:
			switch (modifier.wc)
			{
			case NOUN:
				this.cc = CompoundClass.NounNoun;
				break;
			case ADJECTIVE:
				this.cc = CompoundClass.AdjectiveNoun;
				break;
			case VERB:
				this.cc = CompoundClass.VerbNoun;
				break;
			case PREPOSITION:
				this.cc = CompoundClass.PrepositionNoun;
				break;
			}
			break;
		//classifying adjective compounds:  head is an adjective
		case ADJECTIVE:
			switch (modifier.wc)
			{
			case NOUN:
				this.cc = CompoundClass.NounAdjective;
				break;
			case ADJECTIVE:
				this.cc = CompoundClass.AdjectiveAdjective;
				break;
			
			case VERB:
				this.cc = CompoundClass.VerbAdjective;
				break;
			
			case PREPOSITION:
				this.cc = CompoundClass.PrepositionAdjective;
				break;
			}
			break;
			//classifying verb compounds:  head is a verb
		case VERB:
			switch (modifier.wc)
			{
			case NOUN:
				this.cc = CompoundClass.NounVerb;
				break;
			case ADJECTIVE:
				this.cc = CompoundClass.AdjectiveVerb;
				break;
			
			case VERB:
				this.cc = CompoundClass.VerbVerb;
				break;
			
			case PREPOSITION:
				this.cc = CompoundClass.PrepositionVerb;
				break;
			}
			break;
			//classifying preposition compounds:  head is a preposition
		case PREPOSITION:
			switch (modifier.wc)
			{
			case NOUN:
				this.cc = CompoundClass.NounPreposition;
				break;
			case ADJECTIVE:
				this.cc = CompoundClass.AdjectivePreposition;
				break;
			
			case VERB:
				this.cc = CompoundClass.VerbPreposition;
				break;
			
			case PREPOSITION:
				this.cc = CompoundClass.PrepositionPreposition;
				break;
			}
			break;
		}


		//attaching possible relationships to each compound class
		//not every relationship does make sense for each compound class
		//this classification may lead to better results
		
		logger.info("\nFound: " + this.cc);
		
		switch (this.cc)
		{
		//noun compounds
		case NounNoun:
			//wholepart, partwhole, Against
			//Adjust weighting, since this is very common
			Relationship wp = new WholePart(modifier.morpheme,head.morpheme);
		//	wp.setWeighting(0.5);
			this.possibleRelationships.add(wp);
			
			Relationship pw = new PartWhole(modifier.morpheme,head.morpheme);
		//	pw.setWeighting(0.5);
			this.possibleRelationships.add(pw);
			
			//this.possibleRelationships.add(new Against(modifier.morpheme,head.morpheme));
			this.possibleRelationships.add(new Purpose(modifier.morpheme,head.morpheme));
			this.possibleRelationships.add(new HalfHalf(modifier.morpheme,head.morpheme));
			this.possibleRelationships.add(new SourceResult(modifier.morpheme,head.morpheme));
			this.possibleRelationships.add(new Composition(modifier.morpheme,head.morpheme));
			this.possibleRelationships.add(new PlaceObject(modifier.morpheme,head.morpheme));
			this.possibleRelationships.add(new ActivityActor(modifier.morpheme,head.morpheme));
			this.possibleRelationships.add(new Comparison(modifier.morpheme,head.morpheme));
			
			break;
		case AdjectiveNoun:
			//Specialization, likeness
			this.possibleRelationships.add(new Specialization(modifier.morpheme, head.morpheme));
			this.possibleRelationships.add(new Comparison(modifier.morpheme, head.morpheme));
			this.possibleRelationships.add(new PlaceObject(modifier.morpheme, head.morpheme));
			break;
		case VerbNoun:
			break;
		case PrepositionNoun:
			break;
		
		//adjective compounds
		case NounAdjective:
			this.possibleRelationships.add(new Comparison(modifier.morpheme, head.morpheme));
			
			break;
		case AdjectiveAdjective:
			this.possibleRelationships.add(new HalfHalf(modifier.morpheme,head.morpheme));
			break;
		case VerbAdjective:
			break;
		case PrepositionAdjective:
			break;
		
		//verb compounds
		case NounVerb:

			break;
		case AdjectiveVerb:
			break;
		case VerbVerb:
			break;
		case PrepositionVerb:
			break;
		
		//preposition compounds
		case NounPreposition:
			break;
		case AdjectivePreposition:
			break;
		case VerbPreposition:
			break;
		case PrepositionPreposition:
			break;
		}
	}

	public String getWord() {
		return compound;
	}

	public List<Morpheme> getMorphemes ()
	{
		List<Morpheme> returnList = new LinkedList<Morpheme>();
		returnList.add(modifier);
		returnList.add(head);

		return returnList;
	}

}
