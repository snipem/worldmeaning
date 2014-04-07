package de.h_da.nlp.type.relationship;

public class Specialization extends Relationship {

	public Specialization(String modifier, String head) {
		super();
		
		//balckboard : a board which is black
		this.modifier = modifier;
		this.head = head;
		this.meaning = "Specialization: Kind of "+head + " which is (generally) " + modifier;
		
		this.query.add("\"" + head + " which is " + modifier+"\"");
		this.query.add("\"" + head + " that is " + modifier+"\"");
	}
}
