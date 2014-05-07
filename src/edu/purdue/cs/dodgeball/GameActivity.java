package edu.purdue.cs.dodgeball;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;

public class GameActivity extends Activity {
	
	private GameView view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);		
		view = new GameView(this);		
		RelativeLayout rl = (RelativeLayout) findViewById(R.id.relative_layout);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        rl.addView(view, params);        
		view.start();
	}

	// Pauses game when not focused
    public void onPause() {
        super.onPause();
        view.setActive(false);
    }
	
}
