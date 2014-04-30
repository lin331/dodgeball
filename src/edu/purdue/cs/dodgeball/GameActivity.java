package edu.purdue.cs.dodgeball;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class GameActivity extends Activity {
	
	private GameView view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);		
		view = new GameView(this);		
		RelativeLayout rl = (RelativeLayout) findViewById(R.id.relative_layout);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(480, 800);
        rl.addView(view, params);        
		view.start();
	}

    public void onPause() {
        super.onPause();
        view.setActive(false);
    }
	
}
