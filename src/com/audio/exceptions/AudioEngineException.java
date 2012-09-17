package com.audio.exceptions;

/**
 * AudioEngineException is thrown when some exceptional condition
 * is encountered during audio engine manipulation operation.
 * It could be caused due to illegal audio file format or any exception 
 * due to Input-ouput operation on streams etc.
 * 
 * @author Ashish Chopra
 * @version 1.0
 */

public class AudioEngineException extends Exception {

	public AudioEngineException(String message) {
		super(message);
	}
}
