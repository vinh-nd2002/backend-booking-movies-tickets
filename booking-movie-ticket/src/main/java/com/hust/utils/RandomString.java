package com.hust.utils;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomString {
	private static final byte LEFT_LIMIT = 48;
	private static final byte RIGHT_LIMIT = 122;
	private static final byte LENGTH_MAX = 10;

	public String randomString() {
		Random random = new Random();
		String generatedString = random.ints(LEFT_LIMIT, RIGHT_LIMIT + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(LENGTH_MAX)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		return generatedString;
	}

}
