package org.mike.fuzzy.mf;

public class BellMF implements MembershipFunction {
	double a;
	double b;
	double c;
	
	public BellMF(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	@Override
	public double eval(double x) {
		double inner = (x - c) / a;
		double denom = 1 + Math.abs(Math.pow(inner, 2 * b));
		double res = 1 / denom;
		return res;
	}
}
