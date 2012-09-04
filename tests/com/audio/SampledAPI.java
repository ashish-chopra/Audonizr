package com.audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SampledAPI {

	public static void main(String[] args) {
		
		File in = new File("tests/sounds/sample1.wav");
		System.out.println(in.exists());
		try {
			AudioInputStream as = AudioSystem.getAudioInputStream(in);
			as.
		} catch(UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException x) {
			x.printStackTrace();
		}
		
		
	}
}
