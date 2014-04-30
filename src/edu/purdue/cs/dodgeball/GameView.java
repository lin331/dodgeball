package edu.purdue.cs.dodgeball;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View {
    private Paint p;
    private boolean active;
    private static final int MAX = 50;
    private static final long start = System.currentTimeMillis();
    private static final int moveSpeed = 10;
    
    private int score = 0;
    Player player = new Player(240,400);
    ArrayList<Ball> balls = new ArrayList<Ball>(MAX);
    Random rand = new Random();
    
    public GameView(Context context) {
        super(context);
        p = new Paint();
        p.setTextSize(20);
        active = true;
    }

    public void onDraw(Canvas c) {
    	p.setColor(Color.BLACK);
    	c.drawText("Score: " + score/10, 50, 50, p);
        p.setColor(Color.GREEN);
    	c.drawCircle(player.getX(), player.getY(), 30, p);
    	p.setColor(Color.RED);
    	if (!balls.isEmpty()) {
	    	for (Ball temp : balls) {
	    		c.drawCircle(temp.getX(), temp.getY(), 20, p);
	    	}
    	}
    	p.setColor(Color.BLACK);
    	c.drawText("Score: " + score/10, 50, 50, p);
    }

    public int update(int delta) {
    	if (delta == 0) {
			postInvalidate();
    		return 0;
    	}
    	if (!balls.isEmpty()) {
	    	for (Ball temp : balls) {
	    		Velocity v = temp.getV();
	    		float newX = temp.getX() + v.getX();
	    		float newY = temp.getY() + v.getY();
	    		score++;
	    		boolean isHit = temp.hit(player);
	    		if (isHit) {
	    			active = false;
	    			balls.clear();
	    			Context context = getContext();
	    			Intent intent = new Intent(context, ScoreActivity.class);
	    			Bundle b = new Bundle();
	    			b.putInt("score", score/10);
	    			intent.putExtras(b);
	    			context.startActivity(intent);
	    			return 1;
	    		}
    			temp.setx(newX);
    			temp.sety(newY);
	    	}
			postInvalidate();
    	}
    	return 0;
    }
    
    public void start() {
        new Thread() {
            public void run() {
                long time1 = System.currentTimeMillis();
                long time2;
                while (active) {
                    try {
                        Thread.sleep(25);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    while(time1-start < 2000) {
                    	time1 = System.currentTimeMillis();
                    	update(0);
                    }
                    time2 = System.currentTimeMillis();
                    int delta = (int) (time2 - time1);
                    if (update(delta) == 1) {
    	    			Context context = getContext();
                    	((Activity)context).finish();
                    }
                    time1 = time2;
                    if (rand.nextInt(10) == 5) {
                    	if (balls.size() == MAX) {
                    		int i = 0;
                    		while (i < 15) {
                    			balls.remove(0);
                    			i++;
                    		}
                    	}
                    	if (rand.nextInt(2) == 1) {
                    		balls.add(new Ball(rand.nextInt(400), 0, 0, rand.nextInt(moveSpeed)+10));
                    	}
                    	else {
                    		balls.add(new Ball(0, rand.nextInt(800), rand.nextInt(moveSpeed)+10, 0));
                    	}
                    }
                }
            }
        }.start();
    }
    
	public boolean onTouchEvent(MotionEvent motionEvent) {
		float touchX = motionEvent.getX();
		float touchY = motionEvent.getY();
		float moveSpeed = 10;
		
		float normal = (float) (moveSpeed / Math.hypot(player.getX() - touchX, player.getY() - touchY));
		float newX = (touchX - player.getX()) * normal;
		float newY = (touchY - player.getY()) * normal;
		
		player.setX(player.getX() + newX);
		player.setY(player.getY() + newY);
		
		return true;
	}
	
    public void setActive(boolean active) {
        this.active = active;
    }
}
