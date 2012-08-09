package com.audio;

public class ValidatorTest {

	public static void main(String[] args) {
		Validator object = new Validator(new String[] {"a", "b", "c"});
		object.displayUsageGuide();
	}
}
