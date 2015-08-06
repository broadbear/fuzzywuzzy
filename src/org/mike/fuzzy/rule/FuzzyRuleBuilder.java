package org.mike.fuzzy.rule;

public class FuzzyRuleBuilder {

	FuzzyRule rule;
	
	public FuzzyRuleBuilder() {
		rule = new FuzzyRule();
	}
	
	public FuzzyRule build() {
		return rule;
	}
	
	public FuzzyRuleBuilder antecedent(LinguisticValue lv) {
		Antecedent a = new Antecedent(lv);
		rule.antecedents.add(a);
		return this;
	}
	
	public FuzzyRuleBuilder consequence(LinguisticValue lv) {
		Consequence c = new Consequence(lv);
		rule.consequence = c;
		return this;
	}
	
}
