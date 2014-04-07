package de.h_da.nlp;

/*
Copyright (c) 2010 A. Maraschi, M. Kuech

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

import java.util.logging.Level;
import java.util.logging.Logger;

import de.h_da.nlp.type.relationship.Relationship;

public class WorldMeaning {

	/**
	 * 
	 * @param compound to be found
	 */
	public static void main(String[] args) {

		//Set java util logger
		Logger logger = Logger.getLogger("WorldMeaning");
		logger.setLevel(Level.INFO);

		//Check if enough parameters
		if (args.length == 0)
		{
			usage();
			System.exit(1);
		}

		 
		//Do your job
		CompoundInterpreter ci = new CompoundInterpreter();
		Relationship evalRelationship = ci.analyseCompound(args[0],false);

		//Check if something is found
		if (evalRelationship != null)
			System.out.println(evalRelationship.getMeaning());
	}

	private static void usage() {
		System.err.println("Error. Not enough parameters given!\n\n" +
				"WorldMeaning <compoundedWord>");

	}

}
