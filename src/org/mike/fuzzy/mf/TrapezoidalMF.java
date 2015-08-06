package org.mike.fuzzy.mf;

public class TrapezoidalMF implements MembershipFunction {
	double a;
	double b;
	double c;
	double d;
	
	public TrapezoidalMF(double a, double b, double c, double d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}
	
	@Override
	public double eval(double x) {
		if (x <= a) {
			return 0;
		}
		else if (a <= x && x <= b) {
			return (x - a) / (b - a);
		}
		else if (b <= x && x <= c) {
			return 1;
		}
		else if (c <= x && x <= d) {
			return (d - x) / (d - c);
		}
		else {
			return 0;
		}
	}
}
