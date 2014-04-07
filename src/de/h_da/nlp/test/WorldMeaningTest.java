package de.h_da.nlp.test;

import org.junit.Test;

import de.h_da.nlp.type.MorphemeClass;

public class WorldMeaningTest {

	CompoundTest myTest;

	public WorldMeaningTest()
	{
		myTest = new CompoundTest();
	}

	@Test public void wholePartTest () {
		myTest.compoundTest(new DesiredMeaning(
				"duckfoot",
				"duck",
				"foot",
				MorphemeClass.NOUN,
				MorphemeClass.NOUN,
				"WholePart"
		));
		
		myTest.compoundTest(new DesiredMeaning(
				"doorknob",
				"door",
				"knob",
				MorphemeClass.NOUN,
				MorphemeClass.NOUN,
				"WholePart"
		));
		
		myTest.compoundTest(new DesiredMeaning(
				"doorbell",
				"door",
				"bell",
				MorphemeClass.NOUN,
				MorphemeClass.NOUN,
				"WholePart"
		));
		
//		myTest.compoundTest(new DesiredMeaning(
//				"daylight",
//				"day",
//				"light",
//				MorphemeClass.NOUN,
//				MorphemeClass.NOUN,
//				"WholePart"
//		));
	}
	
	@Test public void partWholeTest () {
		myTest.compoundTest(new DesiredMeaning(
				"pendulumclock",
				"pendulum",
				"clock",
				MorphemeClass.NOUN,
				MorphemeClass.NOUN,
				"PartWhole"
		));
	}
	
	@Test public void purposeTest () {
//		myTest.compoundTest(new DesiredMeaning(
//				"trashbin",
//				"trash",
//				"bin",
//				MorphemeClass.NOUN,
//				MorphemeClass.NOUN,
//				"Purpose"
//		));
//		
		myTest.compoundTest(new DesiredMeaning(
				"wastecontainer",
				"waste",
				"container",
				MorphemeClass.NOUN,
				MorphemeClass.NOUN,
				"Purpose"
		));
	}
	
	@Test public void placeObjectTest () {
		myTest.compoundTest(new DesiredMeaning(
				"southern accent",
				"southern",
				"accent",
				MorphemeClass.ADJECTIVE,
				MorphemeClass.NOUN,
				"PlaceObject"
		));
	}
	
	@Test public void compositionTest () {
		myTest.compoundTest(new DesiredMeaning(
				"stone furniture",
				"stone",
				"furniture",
				MorphemeClass.NOUN,
				MorphemeClass.NOUN,
				"Composition"
		));
		
		myTest.compoundTest(new DesiredMeaning(
				"woodtable",
				"wood",
				"table",
				MorphemeClass.NOUN,
				MorphemeClass.NOUN,
				"Composition"
		));
	}
	
	@Test public void timeObjectTest () {
		myTest.compoundTest(new DesiredMeaning(
				"summer time",
				"summer",
				"time",
				MorphemeClass.NOUN,
				MorphemeClass.NOUN,
				"TimeObject"
		));
	}
	
	@Test public void comparisonTest () {
		myTest.compoundTest(new DesiredMeaning(
				"pumpkin bus",
				"pumpkin",
				"bus",
				MorphemeClass.NOUN,
				MorphemeClass.NOUN,
				"Comparison"
		));
		
//		myTest.compoundTest(new DesiredMeaning(
//				"pumpkin bus",
//				"pumpkin",
//				"bus",
//				MorphemeClass.NOUN,
//				MorphemeClass.NOUN,
//				"Comparison"
//		));
	}

	
	@Test public void activityActorTest () {
		myTest.compoundTest(new DesiredMeaning(
				"dressmaker",
				"dress",
				"maker",
				MorphemeClass.NOUN,
				MorphemeClass.NOUN,
				"ActivityActor"
		));
		
//		myTest.compoundTest(new DesiredMeaning(
//				"ballplayer",
//				"ball",
//				"player",
//				MorphemeClass.NOUN,
//				MorphemeClass.NOUN,
//				"ActivityActor"
//		));
		
		myTest.compoundTest(new DesiredMeaning(
				"doorman",
				"door",
				"man",
				MorphemeClass.NOUN,
				MorphemeClass.NOUN,
				"ActivityActor"
		));
		
		myTest.compoundTest(new DesiredMeaning(
				"businessman",
				"business",
				"man",
				MorphemeClass.NOUN,
				MorphemeClass.NOUN,
				"ActivityActor"
		));
	
	
	}
	
	@Test public void halfHalfTest () {
		myTest.compoundTest(new DesiredMeaning(
				"batman",
				"bat",
				"man",
				MorphemeClass.NOUN,
				MorphemeClass.NOUN,
				"HalfHalf"
		));
	}
	
	@Test public void likenessTest () {
		myTest.compoundTest(new DesiredMeaning(
				"snowwhite",
				"snow",
				"white",
				MorphemeClass.NOUN,
				MorphemeClass.ADJECTIVE,
				"Likeness"
		));
		
//		myTest.compoundTest(new DesiredMeaning(
//				"bluenose",
//				"snow",
//				"white",
//				MorphemeClass.NOUN,
//				MorphemeClass.ADJECTIVE,
//				"Likeness"
//		));

		myTest.compoundTest(new DesiredMeaning(
				"giraffecow",
				"giraffe",
				"cow",
				MorphemeClass.NOUN,
				MorphemeClass.NOUN,
				"HalfHalf"
		));
	}
	
	@Test public void sourceResultTest() {
		
		myTest.compoundTest(new DesiredMeaning(
				"honey glands",
				"honey",
				"glands",
				MorphemeClass.NOUN,
				MorphemeClass.NOUN,
				"SourceResult"
		));
		
	}
	@Test public void specializationTest() {
		
		myTest.compoundTest(new DesiredMeaning(
				"whiteboard",
				"white",
				"board",
				MorphemeClass.ADJECTIVE,
				MorphemeClass.NOUN,
				"Specialization"
		));
		
		myTest.compoundTest(new DesiredMeaning(
				"blackboard",
				"black",
				"board",
				MorphemeClass.ADJECTIVE,
				MorphemeClass.NOUN,
				"Specialization"
		));
		
		myTest.compoundTest(new DesiredMeaning(
				"green-beret",
				"green",
				"beret",
				MorphemeClass.ADJECTIVE,
				MorphemeClass.NOUN,
				"Specialization"
		));

	
	}
	
}
