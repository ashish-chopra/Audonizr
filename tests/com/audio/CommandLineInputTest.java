package com.audio;

import com.audio.exceptions.InputParseException;


public class CommandLineInputTest implements Test {

	private void testIsEmpty() {
		
		// content array is null
		String[] test1 = null;
		CommandLineInput val1 = new CommandLineInput(test1);
		assert (val1.isEmpty());
		
		// content array is empty
		String[] test2 = new String[] {};
		CommandLineInput val2 = new CommandLineInput(test2);
		assert (val2.isEmpty());
		
		// single content array
		String[] test3 = new String[] {"test/sounds/sample1.wav", "-s"};
		CommandLineInput val3 = new CommandLineInput(test3);
		assert (!val3.isEmpty());
	}
	
	private void testGetUsageInstructions() {
		
		String instruction1 = "Usage: java Audonizr <input-file-path> <output-file-path> [-p]\n" + 
							  "<input-file-path>     [mandatory] the name of the file you want to reverse.\n" + 
							  "<output-file-path>    [mandatory] the name of the file generated after the reverse operation.\n" + 
							  "-p                    [optional]  if set, then output file will be played.";
		
		String instruction2 = "";
		
		CommandLineInput cmd = new CommandLineInput(new String[] {});
		assert(cmd.getUsageInstructions().equals(instruction1));
		assert(!cmd.getUsageInstructions().equals(instruction2));
		
	}
	
	
	private void testParse() {
		String[] test1 = new String[]{};
		String[] test2 = {"sample.wav", "output.wav", "-p"};
		String[] test3 = {"-p", "sample.wav", "sample2.wav"};
		String[] test4 = {"sample.wav", "-p"};
		String[] test5 = {"sample.wav", "output.wav", "-o"};
		
		String expected1 = "Incorrect usage: No arguments are passed.";
		String expected2 = "Incorrect usage: Missing mandatory arguments.";
		String expected3 = "Incorrect usage: Illegal flags used.";
		
		// when input is empty
		CommandLineInput cmd = new CommandLineInput(test1);
		try {
			cmd.parse();
		} catch (InputParseException e) {
			assert (e.getMessage().equals(expected1));
		}
		
		// when input is correct
		cmd = new CommandLineInput(test2);
		try {
			cmd.parse();
			assert (cmd.getInputFilePath().equals("sample.wav"));
			assert (cmd.getOutputFilePath().equals("output.wav"));
			assert (cmd.isAutoPlay());
		} catch(InputParseException e) {
			assert (false);
		}
		
		// when input format is incorrect
		cmd = new CommandLineInput(test3);
		try {
			cmd.parse();
		} catch(InputParseException e) {
			assert (e.getMessage().equals(expected2));
		}
		
		// when input format is incorrect
		cmd = new CommandLineInput(test4);
		try {
			cmd.parse();
		} catch(InputParseException e) {
			assert (e.getMessage().equals(expected2));
		}
		
		// when input is illegal
		cmd = new CommandLineInput(test5);
		try {
			cmd.parse();
		} catch(InputParseException e) {
			assert (e.getMessage().equals(expected3));
		}
	}
	
	public void run() {
		testIsEmpty();
		testGetUsageInstructions();
		testParse();
	}
}
