package org.mike.fuzzy.mf;

public class TrianglularMF implements MembershipFunction {
	double a;
	double b;
	double c;

	public TrianglularMF(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	@Override
	public double eval(double x) {
		return Math.max(Math.min((x - a) / (b - a), (c - x) / (c - b)), 0);
	}
}
