package com.audio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;

public class Audio {

	InputStream stream;
	Sample[] samples;
	
	public Audio(InputStream stream) {
		this.stream = stream;
	}
	
	public Sample[] getSamples() {
		if (samples != null) 
			return samples;
	
		byte[] bucket = new byte[getSampleSize()];
		List<Sample> content = new ArrayList<Sample>();
		try {
			while (true) {
				int len = stream.read(bucket);
				if (len == -1) break;
				Sample s = new Sample(bucket);
				content.add(s);
			}
			samples = (Sample[]) content.toArray();
		} catch (IOException e) {
			
		}
		return samples;
	}
	
	public byte[] getBytes() {
		
		if(samples == null)
			getSamples();
		
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		try {
			for (Sample s : samples) {
				byteOut.write(s.getBytes());
			}
		}catch (IOException e) {
			
		}
		return byteOut.toByteArray();
	}
	
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
	
	public AudioFormat getFormat() {
		return ((AudioInputStream) stream).getFormat();
	}
	
	public int getSampleSize() {
		return getFormat().getFrameSize();
	}
}
