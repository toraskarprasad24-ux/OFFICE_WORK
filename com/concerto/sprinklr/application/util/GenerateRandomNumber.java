//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.concerto.sprinklr.application.util;

import java.util.Random;

public class GenerateRandomNumber {
	public GenerateRandomNumber() {
	}

	public static long generateRandomString(int no) {
		return (long)(no + (new Random()).nextInt(9 * no));
	}
}
