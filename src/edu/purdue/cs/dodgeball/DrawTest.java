package edu.purdue.cs.dodgeball;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import java.util.Timer;
import java.util.TimerTask;

@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
public class DrawTest extends View {
    
	private int x, y;
	private final int width = 30;
	Paint paint = new Paint();
        
	public DrawTest(Context context) {
		super(context);
		x = 240;
		y = 400;
		}

	@Override
	public synchronized void onDraw(Canvas canvas) {
		paint.setColor(Color.GREEN);
		canvas.drawCircle(x, y, width, paint);
		paint.setColor(Color.BLACK);
		paint.setTextSize(50);
		int score = 0;
		Timer t = new Timer();
		canvas.drawText("Score: "+score, 150, 50, paint);
		score += 10;	
	}
        
	@Override
	public boolean onTouchEvent(MotionEvent motionEvent) {
		int newx = (int) motionEvent.getX();
		int newy = (int) motionEvent.getY();
		int movementSpeed = 5;
		if (x < newx)
			x+= movementSpeed;
		else if (x > newx)
			x-= movementSpeed;
		if (y < newy)
			y+= movementSpeed;
		else if (y > newy)
			y-= movementSpeed;
		this.invalidate();
		return true;
	}  
}
