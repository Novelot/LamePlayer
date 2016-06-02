package com.novelot.demo.player;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.util.Log;
import jmp123.decoder.IAudio;

public class AndroidAudio implements IAudio {

	private Context context;
	private AudioTrack mAudioTrack;

	public AndroidAudio(Context context) {
		super();
		this.context = context;
	}

	@Override
	public boolean open(int rate, int channels, int bufferSize) {
		Log.v("jmp_test",
				"<open>"
						+ String.format("rate=%d,channels=%d,bufferSize=%d.",
								rate, channels, bufferSize));

		mAudioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, rate,
				AudioFormat.CHANNEL_OUT_STEREO, AudioFormat.ENCODING_PCM_16BIT,
				bufferSize, AudioTrack.MODE_STREAM);
		mAudioTrack.play();
		return true;
	}

	@Override
	public int write(byte[] b, int off, int size) {
		Log.v("jmp_test",
				"<write>" + String.format("off=%d,size=%d", off, size));
		
		return mAudioTrack.write(b, off, size);
	}

	@Override
	public void start(boolean started) {
		Log.v("jmp_test", "<start>" + "isStarted=" + started);
		if (!started)
			mAudioTrack.play();
	}

	@Override
	public void drain() {
		Log.v("jmp_test", "drain");

	}

	@Override
	public void close() {
		Log.v("jmp_test", "close");
		mAudioTrack.stop();
	}

}
