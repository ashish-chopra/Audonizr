/*
 *  File: Audio.java
 *  Date: 20 Sept, 2012
 *  Author: Ashish Chopra
 *  
 */
package com.audio;

/**
 * Sample class represents the collection of bytes that represents
 * the audio sample. The sample data is also available to user in 
 * different forms using public methods.
 * 
 * @author Ashish Chopra
 * @version 1.0
 *
 */
public class Sample {

	
	/**
	 * constructs the sample object abstracting a collection
	 * of bytes as audio sample.
	 * @param s takes an array of bytes as a sample.
	 */
	public Sample(byte[] s) {
		content = s;
	}
	
	/**
	 * gets the size of the sample in bytes.
	 * @return the size in number of bytes as integer.
	 */
	public int size() {
		return content.length;
	}
	
	/**
	 * gets the array of bytes in a sample.
	 * @return array of bytes.
	 */
	public byte[] getBytes() {
		return content;
	}
	
	private byte[] content;			/* array of bytes representing audio samples */
}
