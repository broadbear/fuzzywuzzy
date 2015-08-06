package org.mike.fuzzy.rule;

import java.util.ArrayList;
import java.util.List;

public class FuzzyRule {

	List<Antecedent> antecedents;
	Consequence consequence;

	public FuzzyRule() {
		antecedents = new ArrayList<>();
	}
	
	public List<Antecedent> getAntecedents() {
		return antecedents;
	}

	public Consequence getConsequence() {
		return consequence;
	}
	
}
