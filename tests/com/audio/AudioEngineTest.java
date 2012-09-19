package com.audio;

import java.io.File;

public class AudioEngineTest implements Test{

	File fileIn;
	File fileOut;
	public void run() {
		test_reverse();
	}
	
	public AudioEngineTest() {
		fileIn = new File("tests/sounds/sample1.wav");
		fileOut = new File("tests/sounds/test1.wav");
	}
	private void test_reverse() {
		
	}
}
