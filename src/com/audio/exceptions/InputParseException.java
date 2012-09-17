/*
 *  File: InputParseException.java
 *  Date: 15 Sept, 2012
 *  Author : Ashish Chopra
 *   
 */
package com.audio.exceptions;

/**
 * InputParseException is thrown when some unhandled 
 * condition arises during parsing the input array by
 * CommandLineInput class.
 * 
 * @author Ashish Chopra
 *
 */
public class InputParseException extends Exception {

	public InputParseException(String message) {
		super(message);
	}
}
