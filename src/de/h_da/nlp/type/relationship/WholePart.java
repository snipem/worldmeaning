package de.h_da.nlp.type.relationship;

public class WholePart extends Relationship {

	public WholePart(String modifier, String head) {
		super();
		 
		this.modifier = modifier;
		this.head = head;
		
		this.meaning = "WholePart: "+head + " is part of (the/a/an) " + modifier;
		this.query.add("\"" + head + " is part of the " + modifier+"\"");
		this.query.add("\"" + head + " is part of a " + modifier+"\"");
		this.query.add("\"" + head + " is part of an " + modifier+"\"");
		this.query.add("\"" + head + " is part of " + modifier+"\"");
		
		this.query.add("\"" + head + " belongs to the " + modifier+"\"");
		this.query.add("\"" + head + " belongs to a " + modifier+"\"");
		this.query.add("\"" + head + " belongs to an " + modifier+"\"");
		this.query.add("\"" + head + " belongs to " + modifier+"\"");
		
		this.query.add("\"" + modifier + " has a " + head+"\"");
		this.query.add("\"" + modifier + " has an " + head+"\"");	
		this.query.add("\"" + modifier + " has the " + head+"\"");
		
		this.query.add("\"" + modifier + " having a " + head+"\"");
		this.query.add("\"" + modifier + " having an " + head+"\"");	
		this.query.add("\"" + modifier + " having the " + head+"\"");

	}

}
