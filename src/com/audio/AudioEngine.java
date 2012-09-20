/*
 *  File: AudioEngine.java
 *  Date: 15 Sept, 2012
 *  Author: Ashish Chopra
 *   
 */
package com.audio;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
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
	
	/**
	 * Constructs the audioEngine object which carries out audio manipulation
	 * and writing the audio content from input to output files.
	 * 
	 * @param fileInLocation input file path as String.
	 * @param fileOutLocation output file path as String.
	 * @throws AudioEngineException if the input audio file is unsupported.
	 * 
	 */
	public AudioEngine(String fileInLocation, String fileOutLocation) throws AudioEngineException {
		fileInput = new File(fileInLocation);
		fileOutput = new File(fileOutLocation);
		setupInfrastructure();
	}
	
	/**
	 * setup the audio stream provided the stream is correct format as supported
	 * by the engine.
	 * @throws AudioEngineException
	 */
	private void setupInfrastructure() throws AudioEngineException {
		try {
			 stream = AudioSystem.getAudioInputStream(fileInput); 
		} catch (IOException e) {
			throw new AudioEngineException(e.getMessage());
		} catch (UnsupportedAudioFileException e) {
			throw new AudioEngineException("Unsupported file format. Use WAV format only.");
		}
		
	}
	
	/**
	 * reverse the audio content given by input file and stores the 
	 * result in output file.
	 * 
	 * @throws AudioEngineException
	 */
	public void reverse() throws AudioEngineException {
		
		try {
			Audio data = new Audio(stream);
			data.reverse();
			writeOutputMedia(data);
			
		} catch (IOException e) {
			throw new AudioEngineException("Error occurred while writing output.");
		}
			
	}
		
	/**
	 * writes the audio content to the output file provided
	 * to the engine.
	 * 
	 * @param data Audio object representing audio.
	 * @see com.audio.Audio
	 * 
	 */
	private void writeOutputMedia(Audio data) throws IOException {
		
		AudioInputStream inputstream = new AudioInputStream(new 
				ByteArrayInputStream(data.getBytes()), data.getFormat(), data.getSampleSize());
		AudioSystem.write(inputstream, AudioFileFormat.Type.WAVE, fileOutput);
		
	}
}

