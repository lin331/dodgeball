package edu.purdue.cs.dodgeball;

import org.json.JSONException;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import android.graphics.Color;

public class MainActivity extends Activity {
	DrawTest drawTest;
    private AsyncTask<Void, Void, Void> task;
    private TextView textView_username[] = new TextView[11];
    private TextView textView_score[] = new TextView[11];
    private ProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        progressBar = (ProgressBar)findViewById(R.id.progressBar1);
    } 


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void startGame(View view) {
		Intent intent = new Intent(this, GameActivity.class);
		startActivity(intent);
		task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params)
            {
            	//username and score using string
            	//JavaToMysql.insert_data(username, score);
            	//JavaToMysql.print_data();
                return null;
            }
        }.execute();
	}
	public void showScore(View view){
		System.out.println(JavaToMysql.flag);
        progressBar = (ProgressBar)findViewById(R.id.progressBar1);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setProgress(0);  
		//
		//JavaToMysql.getConnection() run on background
		//User name stored at JavaToMysql.username[] 
		//Highest score stored at JavaToMysql.highscore[]
		//
		task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params)
            {
            	//JavaToMysql.insert_data("LemonBear", "123");
            	JavaToMysql.print_data();
                return null;
            }
        }.execute();
        //
        while(JavaToMysql.flag!=1){
        	try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        progressBar.setVisibility(View.INVISIBLE);
		setContentView(R.layout.highscoure);
	    textView_username[1]= (TextView) findViewById(R.id.textView1Username);
	    textView_username[2]= (TextView) findViewById(R.id.textView2Username);
	    textView_username[3]= (TextView) findViewById(R.id.textView3Username);
	    textView_username[4]= (TextView) findViewById(R.id.textView4Username);
	    textView_username[5]= (TextView) findViewById(R.id.textView5Username);
	    textView_username[6]= (TextView) findViewById(R.id.textView6Username);
	    textView_username[7]= (TextView) findViewById(R.id.textView7Username);
	    textView_username[8]= (TextView) findViewById(R.id.textView8Username);
	    textView_username[9]= (TextView) findViewById(R.id.textView9Username);
	    textView_username[10]= (TextView) findViewById(R.id.textView10Username);
	    textView_score[1]= (TextView) findViewById(R.id.textView1Score);
	    textView_score[2]= (TextView) findViewById(R.id.textView2Score);
	    textView_score[3]= (TextView) findViewById(R.id.textView3Score);
	    textView_score[4]= (TextView) findViewById(R.id.textView4Score);
	    textView_score[5]= (TextView) findViewById(R.id.textView5Score);
	    textView_score[6]= (TextView) findViewById(R.id.textView6Score);
	    textView_score[7]= (TextView) findViewById(R.id.textView7Score);
	    textView_score[8]= (TextView) findViewById(R.id.textView8Score);
	    textView_score[9]= (TextView) findViewById(R.id.textView9Score);
	    textView_score[10]= (TextView) findViewById(R.id.textView10Score);
	    int i;
	    for(i=0;i<10;i++){
	    	if(JavaToMysql.username[i]!=null){
	    		textView_username[i+1].setText(JavaToMysql.username[i]); 
	    		textView_score[i+1].setText(String.valueOf(JavaToMysql.highscore[i])); 
	    	}
	    	else{
	    		textView_username[i+1].setText("Empty"); 
	    		textView_score[i+1].setText("0"); 	    	
	    	}
	    }
	    JavaToMysql.flag=0;
	}
	public void showMenu(View View){
		setContentView(R.layout.activity_main);
	}
}


