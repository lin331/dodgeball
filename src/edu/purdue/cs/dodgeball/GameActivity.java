package edu.purdue.cs.dodgeball;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
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
        // When our activity pauses, we want our view to stop updating its logic.
        // This prevents your application from running in the background, which eats up the battery.
        view.setActive(false);
    }
	
}
