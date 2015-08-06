package org.mike.fuzzy.infer;

import java.util.List;

import org.mike.fuzzy.mf.MembershipFunction;
import org.mike.fuzzy.rule.FuzzyRule;

public interface InferenceProcedure {

	public MembershipFunction induce(List<FuzzyRule> rules, Double ... crispInputs);
	
}
