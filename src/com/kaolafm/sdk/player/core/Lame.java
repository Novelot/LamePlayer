package com.kaolafm.sdk.player.core;

public class Lame {

	static{
		System.loadLibrary("mp3lame");
	}
	
	public static native String getLameVersion();
}
