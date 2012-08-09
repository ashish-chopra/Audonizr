/*
 *  File : Validator.java
 *  Date : 9 Aug, 2012
 *  Author  :Ashish Chopra
 *  
 */
package com.audio;

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
	 * @param args command-line inputs provided by user
	 */
	public Validator(String[] args) {
		this.rawInput = args;
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
		return true;
	}
	
	
	private String[] rawInput;  /* input recevied from user in command-line syntax */
	private Object washedInput; /* validated and tested input parameters from rawInput */
}
