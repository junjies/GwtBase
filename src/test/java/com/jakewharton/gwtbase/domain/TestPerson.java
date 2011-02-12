package com.jakewharton.gwtbase.domain;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.jakewharton.gwtbase.shared.LogUtility;
import junit.framework.Assert;
import junit.framework.TestCase;

public class TestPerson extends TestCase {
	private static final Logger LOGGER = LogUtility.get(TestPerson.class);

	@Override
	protected void setUp() throws Exception {
		LOGGER.log(Level.FINE, "SET UP");
	}

	@Override
	protected void tearDown() throws Exception {
		LOGGER.log(Level.FINE, "TEAR DOWN");
	}

	public void testTesting() {
		LOGGER.log(Level.FINE, "TEST TESTING!");
		Assert.assertEquals(true, true);
	}
}
