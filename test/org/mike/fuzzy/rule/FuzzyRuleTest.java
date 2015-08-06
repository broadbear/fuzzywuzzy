package org.mike.fuzzy.rule;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mike.fuzzy.infer.InferenceProcedure;
import org.mike.fuzzy.infer.Mamdani;
import org.mike.fuzzy.mf.BellMF;
import org.mike.fuzzy.mf.MembershipFunction;

public class FuzzyRuleTest {

	@Test
	public void testBuild() {
		LinguisticValue A = new LinguisticValue(new Universe(0, 100), new BellMF(20, 4, 50));
		LinguisticValue B = new LinguisticValue(new Universe(0, 100), new BellMF(20, 4, 50));
		LinguisticValue C = new LinguisticValue(new Universe(0, 100), new BellMF(20, 4, 50));
		FuzzyRule rule = new FuzzyRuleBuilder().antecedent(A).antecedent(B).consequence(C).build();	
		List<FuzzyRule> rules = new ArrayList<>();
		rules.add(rule);
		InferenceProcedure proc = new Mamdani();
		MembershipFunction muC = proc.induce(rules, 50d, 50d);
		System.out.println();
	}
	
}
