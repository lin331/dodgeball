package edu.purdue.cs.dodgeball;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.graphics.Color;

public class MainActivity extends Activity {
	DrawTest drawTest;
    private AsyncTask<Void, Void, Void> task;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//JavaToMysql.getConnection() run on background
		task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params)
            {
            	JavaToMysql.getConnection();
            	JavaToMysql.print_data();
                return null;
            }
        }.execute();
	} 
	
    protected String doInBackground() {
		
		return null;
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
