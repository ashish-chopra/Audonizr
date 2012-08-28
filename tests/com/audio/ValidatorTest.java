package com.audio;

public class ValidatorTest implements Test {
	
		
	private void test_isInputEmpty() {
		
		// content array is null
		String[] test1 = null;
		Validator val1 = new Validator(test1);
		assert (val1.isInputEmpty());
		
		// content array is empty
		String[] test2 = new String[] {};
		Validator val2 = new Validator(test2);
		assert (val2.isInputEmpty());
		
		// single content array
		String[] test3 = new String[] {"-in"};
		Validator val3 = new Validator(test3);
		assert (!val3.isInputEmpty());
	}
	
	public void run() {
		test_isInputEmpty();
	}
}
