package org.mike.fuzzy.mf;

public class GaussianMF implements MembershipFunction {
	double c;
	double sigma;

	public GaussianMF(double c, double sigma) {
		this.c = c;
		this.sigma = sigma;
	}
	
	@Override
	public double eval(double x) {
		double inner = (x - c) / sigma;
		double num = -.5 * Math.pow(inner, 2);
		double res = Math.pow(Math.E, num);
		return res;
	}
}
