package org.mike.fuzzy.infer;

import java.util.List;

import org.mike.fuzzy.mf.MembershipFunction;
import org.mike.fuzzy.rule.Antecedent;
import org.mike.fuzzy.rule.Consequence;
import org.mike.fuzzy.rule.Fact;
import org.mike.fuzzy.rule.FuzzyRule;
import org.mike.fuzzy.rule.LinguisticValue;

public class Mamdani implements InferenceProcedure {

	TNorm tNorm;
	TConorm tConorm;
	
	public Mamdani() {
		this.tNorm = new MinTNorm();
		this.tConorm = new MaxTConorm();
	}
	
	// TODO: this method is intended to take Fuzzy Set inputs as opposed to Crisp inputs, and needs work.
	public MembershipFunction induce(List<Fact> facts, FuzzyRule rule) { // TODO: extend to multi-fact, multi-rule?
		return new MembershipFunction() {
			@Override
			public double eval(double y) {
				double w = 0;
				for (int i = 0; i < facts.size(); i++) {
					// TODO: some memoization could be in order here. 
					//  We may not know the facts, but the rules are 
					//  well established.
					Fact fact = facts.get(i); // x is A'
					Antecedent antecedent = rule.getAntecedents().get(i); // x is A
					LinguisticValue APrime = fact.getLinguisticValue(); // A'
					LinguisticValue A = antecedent.getLinguisticValue(); // A
					MembershipFunction muAPrime = APrime.getMembershipFunction(); // uA'
					MembershipFunction muA = A.getMembershipFunction(); // uA

					// TODO: actually maxX looks like the 'max operator' which would be the max value of the intersection of A and A'
					// TODO: so find max of min(muA'(x), muA(x)), how!?
					// TODO: w is essentially the highest membership grade of the intersection of A and A'
					// TODO: maybe find the intersection?
//					MembershipFunction muCPrime = this.tNorm.eval(muAPrime, muA); // TODO: what to do here?
					
					double wi = findMax(new MembershipFunction() {
						@Override
						public double eval(double x) {
							return Math.min(muAPrime.eval(x), muA.eval(x));
						}
					});
					
					if (wi < w) {
						w = wi;
					}
				}
				Consequence consequence = rule.getConsequence(); // y is B
				LinguisticValue B = consequence.getLinguisticValue(); // B
				MembershipFunction muB = consequence.getLinguisticValue().getMembershipFunction(); // uB
				double res = Math.min(w, muB.eval(y));
				return res;
			}
		};
	}
	
	protected double findMax(MembershipFunction membershipFunction) {
		// TODO Auto-generated method stub
		return 0;
	}

	public MembershipFunction induce(List<FuzzyRule> rules, Double ... crispInputs) {
		MembershipFunction muCPrime = null;
		for (FuzzyRule rule : rules) {
			MembershipFunction muCPrimei = induce(rule.getAntecedents(), rule.getConsequence(), crispInputs); // C'i
			muCPrime = (muCPrime == null) ? muCPrimei : tConorm.eval(muCPrime, muCPrimei); // C'
		}
		return muCPrime;
	}
	
	/**
	 * induce method that takes multiple antecedents and crispInputs. 
	 * The number of inputs must equal the number of antecedents.
	 * 
	 * A crisp input, for example x, is simply the point on the 
	 * curve, so no worry about intersecting with a fuzzy set like A'.
	 * 
	 * @param antecedents
	 * @param consequence
	 * @param crispInputs
	 * @return
	 */
	MembershipFunction induce(List<Antecedent> antecedents, Consequence consequence, Double ... crispInputs) {
		double w = 0;
		for (int i = 0; i < crispInputs.length; i++) {
			Double x = crispInputs[i];
			Antecedent antecedent = antecedents.get(i); // x is A
			LinguisticValue A = antecedent.getLinguisticValue(); // A
			MembershipFunction muA = A.getMembershipFunction(); // uA
			double wi = muA.eval(x);
			if (wi < w) {
				w = wi;
			}
		}
		LinguisticValue C = consequence.getLinguisticValue(); // C
		MembershipFunction muC = C.getMembershipFunction(); // uC
		MembershipFunction muCPrime = tNorm.eval(w, muC); // uC'
		return muCPrime;
	}
	
	static interface TNorm {		
		public MembershipFunction eval(double w, MembershipFunction mu);
	}
	
	static interface TConorm {
		public MembershipFunction eval(MembershipFunction muCPrime1, MembershipFunction muCPrime2);
	}
	
	static class MinTNorm implements TNorm {
		@Override
		public MembershipFunction eval(double w, MembershipFunction mu) {
			return new MembershipFunction() {
				@Override
				public double eval(double x) {
					return Math.min(w, mu.eval(x));
				};
			};
		}
	}
	
	static class ProductTNorm implements TNorm {
		@Override
		public MembershipFunction eval(double w, MembershipFunction mu) {
			return new MembershipFunction() {
				@Override
				public double eval(double x) {
					return w * mu.eval(x); // TODO: maybe
				};
			};
		}
	}
	
	static class MaxTConorm implements TConorm {
		@Override
		public MembershipFunction eval(MembershipFunction muCPrime1, MembershipFunction muCPrime2) {
			return new MembershipFunction() {
				@Override
				public double eval(double x) {
					return Math.max(muCPrime1.eval(x), muCPrime2.eval(x));
				}
			};
		}
	}
}
