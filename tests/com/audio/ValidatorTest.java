package com.audio;

public class ValidatorTest implements Test {
	
	public void testValidate() {
		System.out.println("Testing Validate....");
		
		// check for correct input
		String[] testInput1 = {
				"-in", 
				"abc.mp3", 
				"-out", 
				"abc-out.mp3"
		};
		Validator v = new Validator(testInput1);
		assert(v.validate());
		
		// check for 0 length input
		String[] testInput2 = {};
		assert(!v.validate());
		
		// check for illegal inputs
		
		System.out.println("Testing Validate passed...");
	}
	
	public void run() {
		testValidate();
	}
}
