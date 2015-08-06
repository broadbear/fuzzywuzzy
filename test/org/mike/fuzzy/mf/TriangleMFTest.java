package org.mike.fuzzy.mf;

import static org.junit.Assert.*;

import org.junit.Test;

public class TriangleMFTest {

	@Test
	public void testEval() {
		MembershipFunction mf = new TrianglularMF(0, 20, 40);
		assertEquals(0, mf.eval(0), 0);
		assertEquals(1, mf.eval(20), 0);
		assertEquals(0, mf.eval(40), 0);
	}
}
