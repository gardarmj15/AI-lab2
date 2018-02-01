import java.util.ArrayList;
import java.util.List;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.CSP;
import aima.core.search.csp.CSPStateListener;
import aima.core.search.csp.Domain;
import aima.core.search.csp.ImprovedBacktrackingStrategy;
import aima.core.search.csp.NotEqualConstraint;
import aima.core.search.csp.SolutionStrategy;
import aima.core.search.csp.Variable;

public class Main {

	private static CSP setupCSP() {
		CSP csp = null;
//		In five houses, each with a different color, live five persons of different nationality,
//		each of whom prefers a different brand of cigarettes, a different drink, and a different pet.
//		The five houses are arranged in a row (no house has more than 2 neighbors).   
//		# The Englishman lives in the red house.
//		# The Spaniard owns the dog.
//		# Coffee is drunk in the green house.
//		# The Ukrainian drinks tea.
//		# The green house is immediately to the right of the ivory house.
//		# The Old Gold smoker owns snails.
//		# Kools are smoked in the yellow house.
//		# Milk is drunk in the middle house.
//		# The Norwegian lives in the first house.
//		# The man who smokes Chesterfields lives in the house next to the man with the fox.
//		# Kools are smoked in the house next to the house where the horse is kept.
//		# The Lucky Strike smoker drinks orange juice.
//		# The Japanese smokes Parliaments.
//		# The Norwegian lives next to the blue house.
//
//		Now, who drinks water? Who owns the zebra?
				
		String[] colors = {"Red", "Green", "Ivory", "Yellow", "Blue"};
		String[] nations = {"Englishman", "Spaniard", "Norwegian", "Ukrainian", "Japanese"};
		String[] cigarettes = {"Old Gold", "Kools", "Chesterfields", "Lucky Strike", "Parliaments"};
		String[] drink = {"Water", "Orange juice", "Tea", "Coffee", "Milk"};
		String[] pet = {"Zebra", "Dog", "Fox", "Snails", "Horse"};

		//Colors
		Variable red = new Variable("Red");
		Variable green = new Variable("Green");
		Variable ivory = new Variable("Ivory");
		Variable yellow = new Variable("Yellow");
		Variable blue = new Variable("Blue");

		//Nations
		Variable english = new Variable("Englishman");
		Variable spanish = new Variable("Spaniard");
		Variable norwegian = new Variable("Norwegian");
		Variable ukrainian = new Variable("Ukrainian");
		Variable japanese = new Variable("Japanese");

		//Cigarettes
		Variable oldGold = new Variable("Old Gold");
		Variable kools = new Variable("Kools");
		Variable chesterfields = new Variable("Chesterfields");
		Variable luckyStrike = new Variable("Lucky Strike");
		Variable parliaments = new Variable("Parliaments");

		//Drinks
		Variable water = new Variable("Water");
		Variable orangeJuice = new Variable("Orange juice");
		Variable tea = new Variable("Tea");
		Variable coffee = new Variable("Coffee");
		Variable milk = new Variable("Milk");

		//Drinks
		Variable zebra = new Variable("Zebra");
		Variable dog = new Variable("Dog");
		Variable fox = new Variable("Fox");
		Variable snails = new Variable("Snails");
		Variable horse = new Variable("Horse");

		List<Variable> variables = new ArrayList<>();

		variables.add(red);
		variables.add(green);
		variables.add(ivory);
		variables.add(yellow);
		variables.add(blue);

		variables.add(english);
		variables.add(spanish);
		variables.add(norwegian);
		variables.add(ukrainian);
		variables.add(japanese);

		variables.add(oldGold);
		variables.add(kools);
		variables.add(chesterfields);
		variables.add(luckyStrike);
		variables.add(parliaments);

		variables.add(water);
		variables.add(orangeJuice);
		variables.add(tea);
		variables.add(coffee);
		variables.add(milk);

		variables.add(zebra);
		variables.add(dog);
		variables.add(fox);
		variables.add(snails);
		variables.add(horse);
		
		csp = new CSP(variables);

		csp.setDomain(red, new Domain(new Integer[]{1,2,3,4,5}));
		csp.setDomain(blue, new Domain(new Integer[]{1,2,3,4,5}));
		csp.setDomain(ivory, new Domain(new Integer[]{1,2,3,4,5}));
		csp.setDomain(green, new Domain(new Integer[]{1,2,3,4,5}));
		csp.setDomain(yellow, new Domain(new Integer[]{1,2,3,4,5}));

		csp.setDomain(english, new Domain(new Integer[]{1,2,3,4,5}));
		csp.setDomain(spanish, new Domain(new Integer[]{1,2,3,4,5}));
		csp.setDomain(norwegian, new Domain(new Integer[]{1}));
		csp.setDomain(ukrainian, new Domain(new Integer[]{1,2,3,4,5}));
		csp.setDomain(japanese, new Domain(new Integer[]{1,2,3,4,5}));

		csp.setDomain(milk, new Domain(new Integer[]{3}));
		csp.setDomain(orangeJuice, new Domain(new Integer[]{1,2,3,4,5}));
		csp.setDomain(coffee, new Domain(new Integer[]{1,2,3,4,5}));
		csp.setDomain(tea, new Domain(new Integer[]{1,2,3,4,5}));
		csp.setDomain(water, new Domain(new Integer[]{1,2,3,4,5}));

		csp.setDomain(oldGold, new Domain(new Integer[]{1,2,3,4,5}));
		csp.setDomain(chesterfields, new Domain(new Integer[]{1,2,3,4,5}));
		csp.setDomain(kools, new Domain(new Integer[]{1,2,3,4,5}));
		csp.setDomain(luckyStrike, new Domain(new Integer[]{1,2,3,4,5}));
		csp.setDomain(parliaments, new Domain(new Integer[]{1,2,3,4,5}));

		csp.setDomain(dog, new Domain(new Integer[]{1,2,3,4,5}));
		csp.setDomain(zebra, new Domain(new Integer[]{1,2,3,4,5}));
		csp.setDomain(fox, new Domain(new Integer[]{1,2,3,4,5}));
		csp.setDomain(snails, new Domain(new Integer[]{1,2,3,4,5}));
		csp.setDomain(horse, new Domain(new Integer[]{1,2,3,4,5}));

		// The Englishman lives in the red house.
		csp.addConstraint(new EqualConstraint(english, red));

		// The Spaniard owns the dog.
		csp.addConstraint(new EqualConstraint(spanish, dog));

		// Coffee is drunk in the green house.
		csp.addConstraint(new EqualConstraint(coffee, green));

		// The Ukrainian drinks tea.
		csp.addConstraint(new EqualConstraint(ukrainian, tea));

		// The green house is immediately to the right of the ivory house.
		csp.addConstraint(new SuccessorConstraint(green, ivory));

		// The Old Gold smoker owns snails.
		csp.addConstraint(new EqualConstraint(oldGold, snails));

		// Kools are smoked in the yellow house.
		csp.addConstraint(new EqualConstraint(kools, yellow));

		// The man who smokes Chesterfields lives in the house next to the man with the fox.
		csp.addConstraint(new DifferByOneConstraint(chesterfields, fox));

		// Kools are smoked in the house next to the house where the horse is kept.
		csp.addConstraint(new DifferByOneConstraint(kools, horse));

		// The Lucky Strike smoker drinks orange juice.
		csp.addConstraint(new EqualConstraint(luckyStrike, orangeJuice));

		// The Japanese smokes Parliaments.
		csp.addConstraint(new EqualConstraint(japanese, parliaments));

		// The Norwegian lives next to the blue house.
		csp.addConstraint(new DifferByOneConstraint(norwegian, blue));

		//Colors
		//Red
		csp.addConstraint(new NotEqualConstraint(red, yellow));
		csp.addConstraint(new NotEqualConstraint(red, blue));
		csp.addConstraint(new NotEqualConstraint(red, ivory));
		csp.addConstraint(new NotEqualConstraint(red, green));
		//Yellow
		csp.addConstraint(new NotEqualConstraint(yellow, blue));
		csp.addConstraint(new NotEqualConstraint(yellow, ivory));
		csp.addConstraint(new NotEqualConstraint(yellow, green));
		//Blue
		csp.addConstraint(new NotEqualConstraint(blue, ivory));
		csp.addConstraint(new NotEqualConstraint(blue, green));
		//Ivory
		csp.addConstraint(new NotEqualConstraint(ivory, green));

		//Nationality
		//English
		csp.addConstraint(new NotEqualConstraint(english, spanish));
		csp.addConstraint(new NotEqualConstraint(english, norwegian));
		csp.addConstraint(new NotEqualConstraint(english, japanese));
		csp.addConstraint(new NotEqualConstraint(english, ukrainian));
		//Spanish
		csp.addConstraint(new NotEqualConstraint(spanish, norwegian));
		csp.addConstraint(new NotEqualConstraint(spanish, japanese));
		csp.addConstraint(new NotEqualConstraint(spanish, ukrainian));
		//Norwegian
		csp.addConstraint(new NotEqualConstraint(norwegian, japanese));
		csp.addConstraint(new NotEqualConstraint(norwegian, ukrainian));
		//Japanese
		csp.addConstraint(new NotEqualConstraint(japanese, ukrainian));

		//Drinks
		//Coffee
		csp.addConstraint(new NotEqualConstraint(coffee, milk));
		csp.addConstraint(new NotEqualConstraint(coffee, water));
		csp.addConstraint(new NotEqualConstraint(coffee, orangeJuice));
		csp.addConstraint(new NotEqualConstraint(coffee, tea));
		//Milk
		csp.addConstraint(new NotEqualConstraint(milk, water));
		csp.addConstraint(new NotEqualConstraint(milk, orangeJuice));
		csp.addConstraint(new NotEqualConstraint(milk, tea));
		//Water
		csp.addConstraint(new NotEqualConstraint(water, orangeJuice));
		csp.addConstraint(new NotEqualConstraint(water, tea));
		//Orange Juice
		csp.addConstraint(new NotEqualConstraint(orangeJuice, tea));

		//Cigs
		//Kools
		csp.addConstraint(new NotEqualConstraint(kools, parliaments));
		csp.addConstraint(new NotEqualConstraint(kools, luckyStrike));
		csp.addConstraint(new NotEqualConstraint(kools, oldGold));
		csp.addConstraint(new NotEqualConstraint(kools, chesterfields));
		//Parliaments
		csp.addConstraint(new NotEqualConstraint(parliaments, luckyStrike));
		csp.addConstraint(new NotEqualConstraint(parliaments, oldGold));
		csp.addConstraint(new NotEqualConstraint(parliaments, chesterfields));
		//Lucky Strike
		csp.addConstraint(new NotEqualConstraint(luckyStrike, oldGold));
		csp.addConstraint(new NotEqualConstraint(luckyStrike, chesterfields));
		//Old Gold
		csp.addConstraint(new NotEqualConstraint(oldGold, chesterfields));

		//Pets
		//Zebra
		csp.addConstraint(new NotEqualConstraint(zebra, dog));
		csp.addConstraint(new NotEqualConstraint(zebra, snails));
		csp.addConstraint(new NotEqualConstraint(zebra, horse));
		csp.addConstraint(new NotEqualConstraint(zebra, fox));
		//Dog
		csp.addConstraint(new NotEqualConstraint(dog, snails));
		csp.addConstraint(new NotEqualConstraint(dog, horse));
		csp.addConstraint(new NotEqualConstraint(dog, fox));
		//Snails
		csp.addConstraint(new NotEqualConstraint(snails, horse));
		csp.addConstraint(new NotEqualConstraint(snails, fox));
		//Horse
		csp.addConstraint(new NotEqualConstraint(horse, fox));

		return csp;
	}

	private static void printSolution(Assignment solution) {
		// TODO print out useful answer
		// You can use the following to get the value assigned to a variable:
		// For debugging it might be useful to print the complete assignment and check whether
		// it makes sense.
		System.out.println("solution:" + solution);
	}
	
	/**
	 * runs the CSP backtracking solver with the given parameters and print out some statistics
	 * @param description
	 * @param enableMRV
	 * @param enableDeg
	 * @param enableAC3
	 * @param enableLCV
	 */
	private static void findSolution(String description, boolean enableMRV, boolean enableDeg, boolean enableAC3, boolean enableLCV) {
		CSP csp = setupCSP();

		System.out.println("======================");
		System.out.println("running " + description);
		
		long startTime, endTime;
		startTime = System.currentTimeMillis();
		SolutionStrategy solver = new ImprovedBacktrackingStrategy(enableMRV, enableDeg, enableAC3, enableLCV);
		final int nbAssignments[] = {0};
		solver.addCSPStateListener(new CSPStateListener() {
			@Override
			public void stateChanged(Assignment arg0, CSP arg1) {
				nbAssignments[0]++;
			}
			@Override
			public void stateChanged(CSP arg0) {}
		});
		Assignment solution = solver.solve(csp);
		endTime = System.currentTimeMillis();
		System.out.println("runtime " + (endTime-startTime)/1000.0 + "s" + ", number of assignments (visited states):" + nbAssignments[0]);
		printSolution(solution);
	}

	/**
	 * main procedure
	 */
	public static void main(String[] args) throws Exception {
		// run solver with different parameters
		findSolution("backtracking + AC3 + most constrained variable + least constraining value", true, true, true, true);
		findSolution("backtracking + AC3 + most constrained variable", true, true, true, false);
		findSolution("backtracking + AC3", false, false, true, false);
		findSolution("backtracking + forward checking + most constrained variable + least constraining value", true, true, false, true);
		findSolution("backtracking + forward checking + most constrained variable", true, true, false, false);
		findSolution("backtracking + forward checking", false, false, false, false);
	}

}
