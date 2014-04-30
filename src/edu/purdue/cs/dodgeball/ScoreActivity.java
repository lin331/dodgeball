package edu.purdue.cs.dodgeball;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ScoreActivity extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score);		
	}
	
	public void sendScore(View view) {
		EditText text = (EditText) findViewById(R.id.namebox);
		String name = text.getText().toString();
		Bundle b = getIntent().getExtras();
		String score = Integer.toString(b.getInt("score"));
		JavaToMysql.insert_data(name, score);
		finish();
	}
}
