package de.h_da.nlp.type.relationship;

public class HalfHalf extends Relationship {

	public HalfHalf(String modifier, String head) {
		
		super();
		
		this.modifier = modifier;
		this.head = head;
		this.meaning = "HalfHalf: "+"half " + modifier + " half " + head;
		
		this.query.add("\"half "+modifier+" half "+head+"\"");
		this.query.add("\"half "+head+" half "+modifier+"\"");
		this.query.add("\"consisting of "+modifier+" and "+head+"\"");
		this.query.add("\"both "+modifier+" and "+head+"\"");
		
	}

}
