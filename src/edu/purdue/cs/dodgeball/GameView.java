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
    private static long start;
    private static final int pRadius = 30;
    private static final int bRadius = 20;
    private static final int moveSpeed = 10;
    private final int width = this.getMeasuredWidth();
    private final int height = this.getMeasuredHeight();
    
    private int score = 0;
    Player player = new Player(240,400);
    ArrayList<Ball> balls;
    Random rand = new Random();
    
    public GameView(Context context) {
        super(context);
        start = System.currentTimeMillis();
        balls = new ArrayList<Ball>(MAX);
        p = new Paint();
        p.setTextSize(20);
        active = true;
        System.out.println(width + " " + height);
    }

    // Draws object onto screen
    public void onDraw(Canvas c) {
    	p.setColor(Color.BLACK);
    	c.drawText("Score: " + score/10, 50, 50, p);
        p.setColor(Color.GREEN);
    	c.drawCircle(player.getX(), player.getY(), pRadius, p);
    	p.setColor(Color.RED);
    	if (!balls.isEmpty()) {
	    	for (Ball temp : balls) {
	    		c.drawCircle(temp.getX(), temp.getY(), bRadius, p);
	    	}
    	}
    	p.setColor(Color.BLACK);
    	c.drawText("Score: " + score/10, 50, 50, p);
    }

    // Updates ball position
    public int update(int flag) {
    	if (flag == 0) {
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
    
    // Thread used to count time
    public void start() {
        new Thread() {
            public void run() {
                long time = System.currentTimeMillis();
                while (active) {
                    try {
                        Thread.sleep(25);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    while(time-start < 2000) {
                        try {
                            Thread.sleep(25);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    	time = System.currentTimeMillis();
                    	update(0);
                    }
                    if (update(1) == 1) {
    	    			Context context = getContext();
                    	((Activity)context).finish();
                    }
                    if (rand.nextInt(10) == 5) {
                    	if (balls.size() == MAX) {
                    		int i = 0;
                    		while (i < 15) {
                    			balls.remove(0);
                    			i++;
                    		}
                    	}
                    	if (rand.nextInt(2) == 1) {
                    		balls.add(new Ball(rand.nextInt(400), 0, 0, rand.nextInt(10)+moveSpeed));
                    	}
                    	else {
                    		balls.add(new Ball(0, rand.nextInt(800), rand.nextInt(10)+moveSpeed, 0));
                    	}
                    }
                }
            }
        }.start();
    }
    
    // Used for player movement
	public boolean onTouchEvent(MotionEvent motionEvent) {
		float touchX = motionEvent.getX();
		float touchY = motionEvent.getY();
		
		float distance = (float) Math.hypot(player.getX() - touchX, player.getY() - touchY);
		float normal = (float) (moveSpeed / distance);
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
