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
import java.util.ArrayList;
import java.util.List;

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

	
	private File fileInput;
	private File fileOutput;
	
	private AudioInputStream stream;
	private int sampleSize;
	private AudioFormat format;
	
	
	public AudioEngine(String fileInLocation, String fileOutLocation) throws AudioEngineException {
		fileInput = new File(fileInLocation);
		fileOutput = new File(fileOutLocation);
		setupInfrastructure();
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
	
	
	public void reverse() throws AudioEngineException {
		
			Sample[] samples = readAudioSamples(stream, sampleSize);
			
			int start = 0, end = samples.length;
			while (start < end) {
				Sample s = samples[start];
				samples[start] = samples[end];
				samples[end] = s;
				start++;
				end--;
			}
			writeAudioSamples(samples, sampleSize, format, fileOutput);
	}
		
	
	private Sample[] readAudioSamples(InputStream stream, int sampleSize) {
		
		byte[] bucket = new byte[sampleSize];
		List<Sample> list = new ArrayList<Sample>();
		try {
			while (true) {
				int len = stream.read(bucket);
				if (len == -1) break;
				Sample s = new Sample(bucket);
				list.add(s);
			}
		} catch (IOException e) {
			
		}
		return (Sample[]) list.toArray();
		
	}
	
	private void writeAudioSamples(Sample[] samples, int size, AudioFormat format, File fileOut) {
		
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		try {
			for(Sample s: samples) {
				stream.write(s.getBytes());
			}
		} catch (IOException e) {
			
		}
		
		
		AudioInputStream inputstream = new AudioInputStream(new 
				ByteArrayInputStream(stream.toByteArray()), format, size);
		try {
			AudioSystem.write(inputstream, AudioFileFormat.Type.WAVE, fileOut);
		} catch (IOException e) {
			System.out.println("Some kind of IO error!");
		}
	}
}

