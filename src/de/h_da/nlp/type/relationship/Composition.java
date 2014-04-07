package de.h_da.nlp.type.relationship;

public class Composition extends Relationship {

	public Composition(String modifier, String head) {
		
		super();
		
		this.modifier = modifier;
		this.head = head;
		this.meaning = "Composition: "+ head + " is made of " + modifier;
		
		this.query.add("\"" + head + " is made of " + modifier+"\"");
		this.query.add("\"" + head + " made of " + modifier+"\"");
	}
}
