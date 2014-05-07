package edu.purdue.cs.dodgeball;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ScoreActivity extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score);		
	}
	
	// Sends score to database
	public void sendScore(View view) {
		EditText text = (EditText) findViewById(R.id.namebox);
		final String name = text.getText().toString();
		Bundle b = getIntent().getExtras();
		final String score = Integer.toString(b.getInt("score"));
		new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params)
            {
        		JavaToMysql.insert_data(name, score);
                return null;
            }
        }.execute();
		finish();
	}
}
