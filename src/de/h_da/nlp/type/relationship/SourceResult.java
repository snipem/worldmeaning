package de.h_da.nlp.type.relationship;

public class SourceResult extends Relationship {

	public SourceResult(String morphemeOne, String morphemeTwo) {
		
		super();
		
		this.modifier = morphemeOne;
		this.head = morphemeTwo;
		this.meaning = "SourceResult: "+head + " consisting of " + modifier;

		this.query.add("\"" + head + " consisting of " + modifier+"\"");
	}

}
