//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.concerto.sprinklr.application.util;

public class GenerateEpochTime {
	public GenerateEpochTime() {
	}

	public long generateTenDigitEpochTime() {
		long currentTimestamp = System.currentTimeMillis() / 1000L;
		if (String.valueOf(currentTimestamp).length() != 10) {
			throw new IllegalStateException("Generated epoch time does not have 10 digits.");
		} else {
			return currentTimestamp;
		}
	}
}
