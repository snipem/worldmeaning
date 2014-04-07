package de.h_da.nlp.type.relationship;

public class PartWhole extends Relationship {

	public PartWhole(String modifier, String head) {
		super();
		
		//armchair: a chair with arms
		this.modifier = modifier;
		this.head = head;
		this.meaning = "PartWhole: "+"(a/an)"+ head + " with (a/an) " + modifier;

		this.query.add("\""+head + " that comes with a " + modifier+"\"");
		this.query.add("\""+modifier+ " attached to a " + head +"\"");
		this.query.add("\""+modifier+ " attached to " + head +"\"");
		this.query.add("\""+modifier+ " attached to the " + head +"\"");
	//	this.query.add("\""+modifier+ " inside the " + head +"\"");
		this.query.add("\""+modifier+ " on top of the " + head +"\"");
		
		//this.query.add("\""+head + " with an " + modifier+" applied\"");
		//this.query.add("\""+head + " with a " + modifier+"\"");
		
	}

}
