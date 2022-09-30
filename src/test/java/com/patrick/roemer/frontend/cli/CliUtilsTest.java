package com.patrick.roemer.frontend.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

class CliUtilsTest {
	
	class MockCliUtils extends CliUtils {
		
		private String getInputReturn;

		public MockCliUtils(String getInputReturn) {
			this.getInputReturn = getInputReturn;
		}
		
		@Override
		public String getInput() {
			return getInputReturn;
		}

		public void setGetInputReturn(String getInputReturn) {
			this.getInputReturn = getInputReturn;
		}
	}
	
	@Test
	void testPickOption() {
		MockCliUtils mcu = new MockCliUtils("5");
		Pair<Boolean,String> pick = mcu.pick();
		assertEquals("5 - Ungueltige Auswahl! Nochmal bitte!", pick.getValue());
		mcu.setGetInputReturn("1");
		pick = mcu.pick();
		assertEquals("scissors", pick.getValue());
	}
	
	@Test
	void testProcessContinue() {
		MockCliUtils mcu = new MockCliUtils("y");
		assertTrue(mcu.processContinue());
		mcu.setGetInputReturn("Y");
		assertTrue(mcu.processContinue());
		mcu.setGetInputReturn("n");
		assertFalse(mcu.processContinue());
		mcu.setGetInputReturn("test");
		assertFalse(mcu.processContinue());
		mcu.setGetInputReturn("1");
		assertFalse(mcu.processContinue());
		
		
	}
}