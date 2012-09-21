/*
 *  File: Audio.java
 *  Date: 15 Sept, 2012
 *  Author: Ashish Chopra
 *  
 */
package com.audio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;

/**
 * Audio class represents the audio data read from a AudioStream
 * given to the object during construction. The class is mutable.
 * The manipulation done on the audio data are persisted in place.
 * 
 * @author Ashish Chopra
 * @version 1.0
 * 
 */
public class Audio {

	
	/**
	 * Creates an Audio object given an audio stream.
	 * @param stream a valid audio stream.
	 */
	public Audio(InputStream stream) {
		this.stream = stream;
	}
	
	/**
	 * gets the array of samples contained in the audio content.
	 * @return array of samples.
	 */
	public Sample[] getSamples() {
		if (samples != null) 
			return samples;
	
		int size = getSampleSize();
		byte[] bucket;
		ArrayList<Sample> content = new ArrayList<Sample>();
		try {
			while (true) {
				bucket = new byte[size];
				int len = stream.read(bucket);
				if (len == -1) break;
				Sample s = new Sample(bucket);
				content.add(s);
			}
			samples = content.toArray(new Sample[content.size()]);
		} catch (IOException e) {
			
		}
		return samples;
	}
	
	/**
	 * gets the array of bytes representing audio content.
	 * @return
	 */
	public byte[] getBytes() {
		
		if(samples == null)
			getSamples();
		
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		try {
			for (Sample s : samples) {
				byteOut.write(s.getBytes());
			}
			byteOut.flush();
		}catch (IOException e) {
			System.out.println("io ex in getBytes()");
		}
		return byteOut.toByteArray();
		
	}
	
	/**
	 * reverses the samples in the audio stream in place.
	 * 
	 */
	public void reverse() {
		getSamples();
		
		int start = 0, end = samples.length - 1;
		while (start < end) {
			Sample s = samples[start];
			samples[start] = samples[end];
			samples[end] = s;
			start++;
			end--;
		}
	}
	
	/**
	 * gets the format object representing the format
	 * of audio content.
	 * @return AudioFormat representation of audio stream
	 */
	public AudioFormat getFormat() {
		return ((AudioInputStream) stream).getFormat();
	}
	
	/**
	 * gets the size of each sample in number of bytes.
	 * @return size of sample as integer.
	 */
	public int getSampleSize() {
		return getFormat().getFrameSize();
	}
	
	public int getSampleCount() {
		if(samples == null)
			getSamples();
		return samples.length;
	}
	
	/* Private Instance Variables */
	
	private InputStream stream;  /* Audio stream of the audio file */
	private Sample[] samples;    /* Audio representation in array of samples */
}
