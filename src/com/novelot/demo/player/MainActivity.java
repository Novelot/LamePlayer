package com.novelot.demo.player;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.kaolafm.sdk.player.core.Lame;
import com.kaolafm.sdk.player.core.MiniPlayer;

public class MainActivity extends Activity {

	private TextView tv;
	private EditText etPath;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		{
			File f = new File("/sdcard/novelot/");
			if (!f.exists()) {
				f.mkdirs();
			}
		}
		setContentView(R.layout.activity_main);
		tv = (TextView) findViewById(R.id.tv);
		etPath = (EditText) findViewById(R.id.etPath);

		tv.setText(Lame.getLameVersion());
	}

	public void play(View v) {
//		final String path = "http://www.novelot.com/test.mp3";// etPath.getText().toString();
		final String path = "/sdcard/novelot/test.mp3";// etPath.getText().toString();
		//

		new Thread() {
			public void run() {
				MiniPlayer player = new MiniPlayer(new AndroidAudio(getApplicationContext()));
				String msg = "error";
				try {
					msg = player.open(path);
				} catch (IOException e) {
					e.printStackTrace();
				}
				Log.v("jmp_test", "<Activity>" + msg);
				player.run();
			};
		}.start();

	}
}
