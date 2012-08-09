/*
 *  File : Validator.java
 *  Date : 9 Aug, 2012
 *  Author  :Ashish Chopra
 *  
 */
package com.audio;

import java.io.File;

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

	private static final boolean AUTO_PLAY_ON = true;
	private static final boolean AUTO_PLAY_OFF = false;
	
	/**
	 * Creates a validator object 
	 * @param args command-line inputs provided by user
	 */
	public Validator(String[] args) {
		this.rawInput = args;
		this.inputAudio = null;
		this.outputAudio = null;
		this.autoPlay = AUTO_PLAY_OFF;
	}
	
	/**
	 * 
	 * displays the usage guide to the user
	 * for the all supporting command-line flags and
	 * arguments.
	 * 
	 */
	public void showUsageInstruction() {
		System.out.println("usage: java Audonizr -in <file-name> [-out <file-name>] \n");
		System.out.println("The most commonly used flags with Audonizr are: ");
		System.out.println("  -in   Specifies the input file location to the utility.");
		System.out.println("  -out  Specifies the ouput file location to the utility.");
		System.out.println("\nArguments in [] are optional and rest are mandatory.");
	}
	

	public boolean validate() {
		if (isInputEmpty()) {
			return false;
		}
		
		boolean success = false;
		boolean mandatory = false;
		for (int i = 0; i < rawInput.length; i++) {
			String text = rawInput[i];
			int rank = getCommandId(text);
			String argument = getCommandArguments(++i);
			switch (rank) {
			case 0 :
			//	inputAudio = validateAudioFile(argument);
				break;
			case 1 : 
				break;
			default : 
				System.out.println("Illegal Characters: " + text);
				return false;
			}
		}
		return true;
	}
	
	private String getCommandArguments(int index) {
		String argument = null;
		try {
			argument = rawInput[index];
		} catch (ArrayIndexOutOfBoundsException e) {}
		return argument;
	}
	
	private int getCommandId(String flag) {
		flag = flag.toLowerCase();
		if (flag.equals("-in"))
			return 0;
		else if (flag.equals("-out"))
			return 1;
		return -1;
	}
	
	/**
	 * returns true if input array is empty.
	 * @return true if input array is empty, false otherwise.
	 */
	private boolean isInputEmpty() {
		return (rawInput.length == 0) ? true : false;
	}
	
	private String[] rawInput;  /* input recevied from user in command-line syntax */
	private File inputAudio;    /* input audio file */
	private File outputAudio;   /* output audio file */
	private boolean autoPlay;   /* set auto play feature on/off */
}
