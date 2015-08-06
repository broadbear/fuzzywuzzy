package org.mike.fuzzy.rule;

import org.mike.fuzzy.mf.MembershipFunction;

public class LinguisticValue {

	Universe universe;
	MembershipFunction membershipFunction;

	public LinguisticValue(Universe universe, MembershipFunction membershipFunction) {
		this.universe = universe;
		this.membershipFunction = membershipFunction;
	}

	public Universe getUniverse() {
		return universe;
	}
	
	public MembershipFunction getMembershipFunction() {
		return membershipFunction;
	}
}
