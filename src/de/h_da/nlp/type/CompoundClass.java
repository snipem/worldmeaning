package de.h_da.nlp.type;

/**
 * enum class for representing possible compounds:
 * 
 * Modifier 		Head 			Compound
	noun 			noun 			football
	adjective 		noun 			blackboard
	verb 			noun 			breakwater
	preposition 	noun 			underworld
	noun 			adjective 		snowwhite
	adjective 		adjective 		blue-green
	verb 			adjective 		tumbledown
	preposition 	adjective 		over-ripe
	noun 			verb 			browbeat
	adjective 		verb 			highlight
	verb 			verb 			freeze-dry
	preposition		verb 			undercut
	noun 			preposition 	love-in
	adjective 		preposition 	forthwith
	verb 			preposition 	takeout
	preposition 	preposition 	without
	
 * @author ali
 *
 */
public enum CompoundClass {
	
	NounNoun, AdjectiveNoun, NounAdjective, 
	AdjectiveAdjective, NounPreposition, NounVerb, 
	VerbNoun, PrepositionNoun, PrepositionPreposition, 
	VerbPreposition, AdjectivePreposition, PrepositionVerb,
	VerbVerb, AdjectiveVerb, PrepositionAdjective, VerbAdjective
	
}
