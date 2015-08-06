package org.mike.fuzzy.mf;

import static org.junit.Assert.*;

import org.junit.Test;

public class BellMFTest {

	@Test
	public void testEval() {
		MembershipFunction mf = new BellMF(20, 4, 50);
		assertEquals(0, mf.eval(20), .02);
		assertEquals(1, mf.eval(50), 0);
		assertEquals(0, mf.eval(80), .02);
	}
}
