/*
 *  File: AudioEngine.java
 *  Date: 15 Sept, 2012
 *  Author: Ashish Chopra
 *   
 */
package com.audio;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.audio.exceptions.AudioEngineException;

/**
 * AudioEngine class performs manipulations on audio data.
 * It is the main processing unit of the utility. It accepts
 * the audio file path and audio file output path as inputs 
 * to the constructor.
 * 
 * 
 * @author Ashish Chopra
 * @version 1.0
 * 
 */
public class AudioEngine {

	private static final int BUCKET_SIZE = 1024;
	
	private byte[] bucket;
	private byte[] data;
	private File fileInput;
	private File fileOutput;
	
	private AudioInputStream stream;
	private int sampleSize;
	private AudioFormat format;
	
	private byte[] extractBytes(InputStream stream) {
		
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		try {
			while (true) {
				int len = stream.read(bucket);
				if (len == -1) break;
				byteOut.write(bucket, 0, len);
			}
		} catch (IOException e) {
			
		}
		return byteOut.toByteArray();
	}
	
	private void setupInfrastructure() throws AudioEngineException {
		AudioInputStream stream = null;
		try {
			 stream = AudioSystem.getAudioInputStream(fileInput);
			 format = stream.getFormat();
			 sampleSize = format.getFrameSize();
			 
		} catch (IOException e) {
			throw new AudioEngineException(e.getMessage());
		} catch (UnsupportedAudioFileException e) {
			throw new AudioEngineException("Unsupported file format. Use WAV format only.");
		}
		
	}
	
	public AudioEngine(String fileInLocation, String fileOutLocation) {
		fileInput = new File(fileInLocation);
		fileOutput = new File(fileOutLocation);
		bucket = new byte[BUCKET_SIZE];
	}
	
	public void reverse() throws AudioEngineException {
			setupInfrastructure();
			data = extractBytes(stream);
			reverseSamplewise(data, sampleSize);
			writeSamples(data, sampleSize, format, fileOutput);
	}
		
	private void reverseSamplewise(byte[] data, int size) {
		int start = 0, end = data.length - size;
		while (start < end) {
			start += size;
			end -= size;
		}
	}
	
	private void writeSamples(byte[] data, int size, AudioFormat format, File fileOut) {
		AudioInputStream stream = new AudioInputStream(new ByteArrayInputStream(data), format, size);
		try {
			AudioSystem.write(stream, AudioFileFormat.Type.WAVE, fileOut);
		} catch (IOException e) {
			System.out.println("Some kind of IO error!");
		}
		
	}
}

