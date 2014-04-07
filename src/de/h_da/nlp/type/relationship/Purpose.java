package de.h_da.nlp.type.relationship;

public class Purpose extends Relationship {
	public Purpose(String modifier, String head) {
		
		super();
		
		this.modifier = modifier;
		this.head = head;
		this.meaning = "Purpose: "+head + " used for (the|a) " + modifier;

		this.query.add("\"" + head + " used for " + modifier+"\"");
		this.query.add("\"" + head + " used for a " + modifier+"\"");
		this.query.add("\"" + head + " used for an " + modifier+"\"");
		this.query.add("\"" + head + " used for the " + modifier+"\"");
		this.query.add("\"" + head + " used for " + modifier+"\"");
		this.query.add("\"" + head + " using for " + modifier+"\"");
		this.query.add("\"" + head + " using for the " + modifier+"\"");
		this.query.add("\"" + head + " using for a " + modifier+"\"");
		this.query.add("\"" + head + " using for an " + modifier+"\"");
		this.query.add("\"" + head + " against the " + modifier+"\"");
		this.query.add("\"" + head + " against " + modifier+"\"");
		this.query.add("\"" + head + " containing " + modifier+"\"");
		this.query.add("\"" + head + " containing " + modifier+"s\"");
		//		this.query.add("\"" + head + " for " + modifier+"\"");
	}

}
