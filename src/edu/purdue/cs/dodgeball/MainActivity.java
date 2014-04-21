package edu.purdue.cs.dodgeball;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.graphics.Color;

public class MainActivity extends Activity {
	DrawTest drawTest;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void startGame(View view) {
		drawTest = new DrawTest(this);
		setContentView(drawTest);
	}

}
