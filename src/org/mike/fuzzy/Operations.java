package org.mike.fuzzy;

public class Operations {

	static public double con(double term) {
		return Math.pow(term, 2);
	}
	
	static public double dil(double term) {
		return Math.pow(term, 0.5);
	}
	
	static public double not(double term) {
		return 1 - term;
	}
	
	static public double and(double termA, double termB) {
		return Math.min(termA, termB);
	}
	
	static public double or(double termA, double termB) {
		return Math.max(termA, termB);
	}
	
	static public double intensifier(double term) {
		if (0 <= term && term <= 0.5) {
			return 2 * Math.pow(term, 2);
		}
		else { // (0.5 <= term && term <= 1)
			return not(2) * Math.pow(not(term), 2);
		}
	}
}
