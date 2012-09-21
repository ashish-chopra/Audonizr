package com.audio;

import com.audio.exceptions.AudioEngineException;
import com.audio.exceptions.InputParseException;

/**
 * 
 * Audonizr is a command-line utility to reverse an audio file.
 * This utility is useful in studying the backmasked audio files
 * which hides the cryptographic messages.
 * 
 * It accepts command-line options and flags. To know its usage type
 * <code>java Audonizr</code>
 * 
 * @author Ashish Chopra
 * @version 1.0
 *
 */
public class Audonizr {

	public static void main(String[] args) {
		
		CommandLineInput cmd = null;
		AudioEngine engine = null;
		try {
			cmd = new CommandLineInput(args);
			cmd.parse();
			engine = new AudioEngine(cmd.getInputFilePath(), cmd.getOutputFilePath());
			engine.reverse();
			
		} catch (InputParseException e) {
			
			System.out.println(e.getMessage());
			System.out.println(cmd.getUsageInstructions());
			
		} catch (AudioEngineException e) {
			
			System.out.println(e.getMessage());
			System.out.println(cmd.getUsageInstructions());
			
		}
	}
}
