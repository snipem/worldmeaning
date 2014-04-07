package de.h_da.nlp.type.relationship;

public class PlaceObject extends Relationship {

	public PlaceObject(String modifier, String head) {
		
		super();
		
		this.modifier = modifier;
		this.head = head;
		this.meaning = "PlaceObject: "+head + " from " + modifier;
		
		this.query.add("\"" + head + " that is from " + modifier+"\"");
		this.query.add("\"" + head + " that is from the " + modifier+"\"");
		this.query.add("\"" + head + " origined from " + modifier+"\"");
		this.query.add("\"" + head + " origined from the " + modifier+"\"");
		this.query.add("\"" + head + " that comes from " + modifier+"\"");
		this.query.add("\"" + head + " that comes from the " + modifier+"\"");
		
//		this.query.add("\"coming from " + modifier + " " + head+"\"");
//		this.query.add("\"origined in " + modifier + " " + head+"\"");
//		this.query.add("\"came from " + modifier + " " + head+"\"");
// 
		this.query.add("\"" + head + " located in " + modifier+"\"");
		
	}

}
