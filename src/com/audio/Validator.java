/*
 *  File : Validator.java
 *  Date : 9 Aug, 2012
 *  Author  :Ashish Chopra
 *  
 */
package com.audio;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Validator is a helper class for Audonizr utility to
 * check the command line inputs and flags for their correct
 * syntax and usage. 
 * 
 * @author Ashish Chopra
 * @version 1.0
 *
 */
public class Validator {
	
	
	/**
	 * Creates a validator object 
	 * @param args String array of inputs provided by user.
	 */
	public Validator(String[] args) {
		this.rawInput = args;
		this.inputAudio = null;
		this.outputAudio = null; 
		constructFlagCollection();
	}
	
	/**
	 * 
	 * displays the usage guide to the user
	 * for the all supporting command-line flags and
	 * arguments.
	 * 
	 */
	public void showUsageInstruction() {
		System.out.println("usage: java Audonizr -in <file-name> [-out <file-name>] [-play on|off] [-help] \n");
		System.out.println("The most commonly used flags with Audonizr are: ");
		System.out.println("  -in   <filename>   Specifies the input file location to the utility.");
		System.out.println("  -out  <filename>   Specifies the ouput file location to the utility.");
		System.out.println("\nArguments in [] are optional and rest are mandatory.");
	}
	
	/**
	 * returns true if input array is empty.
	 * @return true if input array is empty, false otherwise.
	 */
	public boolean isInputEmpty() {
		return (rawInput == null || rawInput.length == 0) ? true : false;
	}
	
	/**
	 * validates the input commands stored in the
	 * validator object against the set of commands for their
	 * presence and correct syntax.
	 * 
	 * @return true if all commands are successfully parsed, false otherwise.
	 */
	public boolean validate() {
		if (isInputEmpty()) {
			return false;
		}
		boolean success = false;
		for (int i = 0; i < rawInput.length; i++) {
			String cmd = rawInput[i];
			if (isValidCommand(cmd)) {
				int code = getCommandCode(cmd);
				String args = getCommandArguments(++i);
				success = setArguments(code, args);
				if(!success) break;
			} else {
				success = false;
				break;
			}
			
		}
		
		return success;
	}
	
	private boolean setArguments(int code, String args) {
		boolean success = false;
		switch(code) {
		case 1:
			if(args == null) {
				System.out.println("Null argument passed.");
				return false;
			}
			inputAudio = checkFileArgs(args);
			break;
		case 2:
			if(args == null) {
				System.out.println("Null Argument passed");
				return false;
			}
			outputAudio = checkFileArgs(args);
			break;
		default:
			System.out.println("Unreachable code");
			success = false;
		}
		return success;
	}
	
	private File checkFileArgs(String args) {
		File f = new File(args);
		if(f.isFile()) return f;
		System.out.println("Invalid file format.");
		return null;
	}
	
	private boolean isValidCommand(String cmd) {
		return getCommandCode(cmd) != -1 ? true : false;
	}
	/**
	 * gets the input audio file from the specified
	 * flag options.
	 * @return File object for input audio file
	 */
	public File getInputSource() {
		return inputAudio;
	}
	
	/**
	 * gets the output audio file name from the specified
	 * flag options
	 * @return File object for output file
	 */
	public File getOutputSource() {
		return outputAudio;
	}
	
	private void constructFlagCollection() {
		flags = new HashMap<String, Integer>();
		flags.put("-in", 1);
		flags.put("-out", 2);
		
	}

	private int getCommandCode(String argument) {
		String key = argument.toLowerCase();
		if (flags.containsKey(key)) {
			return flags.get(key);
		}
		return -1;
	}
	
	private String getCommandArguments(int index) {
		String argument = null;
		try {
			argument = rawInput[index];
		} catch (ArrayIndexOutOfBoundsException e) {}
		return argument;
	}
	

	
	private int cursor;
	private Map<String, Integer> flags; /* Collection of flags used in this tool */
	private String[] rawInput;  		/* input recevied from user in command-line syntax */
	private File inputAudio;    		/* input audio file */
	private File outputAudio;   		/* output audio file */
	
}
