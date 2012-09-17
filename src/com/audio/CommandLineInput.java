/*
 *  File : CommandLineInput.java
 *  Date : 9 Aug, 2012
 *  Author  :Ashish Chopra
 *  
 */
package com.audio;

import com.audio.exceptions.InputParseException;


/**
 * 
 * CommandLineInput is a helper class for Audonizr utility to
 * check the syntax of inputs sent via command line into the 
 * utility.
 * 
 * @author Ashish Chopra
 * @version 1.0
 *
 */
public class CommandLineInput {
	
	/**
	 * Creates an object 
	 * @param args String array of inputs provided by user.
	 */
	public CommandLineInput(String[] args) {
		this.rawInput = args;
		this.autoPlay = false;
		
	}
		
	public void parse() throws InputParseException {
		if (isEmpty())
			throw new InputParseException("Incorrect usage: No arguments are passed.");

		fileInPath = getMandatoryArgsAt(0);
		fileOutPath = getMandatoryArgsAt(1);
		autoPlay = getOptionalArgsAt(2);
	}
	
	private String getMandatoryArgsAt(int index) throws InputParseException {
		if(rawInput.length <= index || isFlag(rawInput[index])) 
			throw new InputParseException("Incorrect usage: Missing mandatory arguments.");
		else
			return rawInput[index];
	}

	private boolean getOptionalArgsAt(int index) throws InputParseException {
		if(rawInput.length <= index)
			return false;
		else if (isFlag(rawInput[index]) && rawInput[index].equals("-p"))
			return true;
		else 
			throw new InputParseException("Incorrect usage: Illegal flags used.");
	}
	
	private boolean isFlag(String flag) {
		return flag.startsWith("-");
	}
	
	/**
	 * tests whether the input array is empty.
	 * @return <code>true</code> if it is empty, <code>false</code> otherwise.
	 */
	public boolean isEmpty() {
		return (rawInput == null || rawInput.length == 0) ? true : false;
	}
	
	
	/**
	 * returns whether auto playback option is set to true.
	 * @return <code>true</code> if set, <code>false</code> otherwise.
	 */
	public boolean isAutoPlay() {
		return autoPlay;
	}
	
	/**
	 * gets the command line usage instructions of the utility
	 * @return the string representation of the instructions.
	 */
	public String getUsageInstructions() {
		StringBuilder sb = new StringBuilder();
		sb.append("Usage: java Audonizr <input-file-path> <output-file-path> [-p]\n");
		sb.append("<input-file-path>     [mandatory] the name of the file you want to reverse.\n");
		sb.append("<output-file-path>    [mandatory] the name of the file generated after the reverse operation.\n");
		sb.append("-p                    [optional]  if set, then output file will be played.");
		
		return sb.toString();
	}
	
	public String getInputFilePath() {
		return fileInPath;
	}
	
	public String getOutputFilePath() {
		return fileOutPath;
	}
	
	private String[] rawInput;
	private String fileInPath;
	private String fileOutPath;
	private boolean autoPlay;
}
