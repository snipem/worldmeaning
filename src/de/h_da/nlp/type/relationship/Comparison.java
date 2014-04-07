package de.h_da.nlp.type.relationship;

public class Comparison extends Relationship {

	public Comparison(String modifier, String head) {
		
		super();
		
		this.modifier = modifier;
		this.head = head;
		this.meaning = "Comparison: "+ head + " that looks like a " + modifier;
		
		this.query.add("\"" + head + " that looks like " + modifier+"\"");
		this.query.add("\"" + head + " that looks like a " + modifier+"\"");
		this.query.add("\"" + head + " that tastes like " + modifier+"\"");
		this.query.add("\"" + head + " that tastes like a " + modifier+"\"");
		this.query.add("\"" + head + " that smells like " + modifier+"\"");
		this.query.add("\"" + head + " that smells like a " + modifier+"\"");
		this.query.add("\"" + head + " with the same color as "+ modifier+"\"");
//		this.query.add("\"" + head + " like the " + modifier+"\"");
//		this.query.add("\"" + head + " like a " + modifier+"\"");
//		this.query.add("\"" + head + " like an " + modifier+"\"");
	}
}
