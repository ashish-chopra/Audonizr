package com.audio;

public class Sample {

	private byte[] content;
	
	public Sample(byte[] s) {
		content = s;
	}
	
	public int size() {
		return content.length;
	}
	
	public byte[] getBytes() {
		return content;
	}
}
