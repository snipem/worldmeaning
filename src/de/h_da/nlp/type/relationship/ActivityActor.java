package de.h_da.nlp.type.relationship;

public class ActivityActor extends Relationship {

	public ActivityActor(String modifier, String head) {
		super();
	 
		this.modifier = modifier;
		this.head = head;
		this.meaning = "ActivityActor: (a/an ) "+head + " occupied in " + modifier;
		
		this.query.add("\"" + head + " who is working in " + modifier+"\"");
		this.query.add("\"" + head + " who works in " + modifier+"\"");
		this.query.add("\"" + head + " who works with " + modifier+"\"");
		this.query.add("\"" + head + " who works for " + modifier+"\"");
		this.query.add("\"" + head + " who works for the " + modifier+"\"");
		
		
		this.query.add("\"" + head + " who is occupied in " + modifier+"\"");
		this.query.add("\"" + head + " who is occupied with" + modifier+"\"");
		this.query.add("\"" + head + " who is occupied in " + modifier+"\"");
		
		this.query.add("\"" + head + " who is engaged in " + modifier+"\"");
		this.query.add("\"" + head + " who is engaged with " + modifier+"\"");
		
	//	this.query.add("\"" + head + " famous of " + modifier+"\"");
		this.query.add("\"" + head + " who is famous of " + modifier+"\"");
		
		this.query.add("\"who works as a "+modifier+head+" \"");
		this.query.add("\"who occupied as a "+modifier+head+" \"");
		
		this.query.add("\"the " + head + " doing " + modifier+"\"");
		this.query.add("\"a " + head + " doing " + modifier+"\"");
		this.query.add("\"a " + head + " who does " + modifier+"\"");
		
		this.query.add("\"a " + head + " who is a " + modifier+"\"");
		this.query.add("\"a " + head + " who is a " + modifier+"\"");
		
	//	this.query.add("\"he is a "  + modifier+ head +"\"");
	}

}
