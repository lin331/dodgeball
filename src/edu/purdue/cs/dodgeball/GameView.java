package edu.purdue.cs.dodgeball;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View {
	// Of course, this stuff should be in its own object, but just for this example..
    //private float position; // Where our dot is
    //private float velocity; // How fast the dot's moving

    private Paint p; // Used during onDraw()
    private boolean active; // If our logic is still active

    Player player = new Player(240,400);
    ArrayList<Ball> balls = new ArrayList<Ball>(50);
    Random rand = new Random();
    
    public GameView(Context context) {
        super(context);
        
        p = new Paint();
        active = true;
    }

    // We draw everything here. This is by default in its own thread (the UI thread).
    // Let's just call this thread THREAD_A.
    public void onDraw(Canvas c) {
        p.setColor(Color.GREEN);
    	c.drawCircle(player.getX(), player.getY(), 30, p);
    	p.setColor(Color.RED);
    	for (Ball temp : balls) {
    		c.drawCircle(temp.xcoord(), temp.ycoord(), 20, p);
    	}
    }

    // This just updates our position based on a delta that's given.
    public void update(int delta) {
    	for (Ball temp : balls) {
    		Velocity v = temp.vel();
    		temp.setx(temp.xcoord() + v.xspeed());
    		temp.sety(temp.ycoord() + v.yspeed());
    	}
		postInvalidate(); // Tells our view to redraw itself, since our position changed.
    }

    // The important part!
    // This starts another thread (let's call this THREAD_B). THREAD_B will run completely
    // independent from THREAD_A (above); therefore, FPS changes will not affect how
    // our velocity increases our position.
    public void start() {
        new Thread() {
            public void run() {
                // Store the current time values.
                long time1 = System.currentTimeMillis();
                long time2;

                // Once active is false, this loop (and thread) terminates.
                while (active) {
                    try {
                        // This is your target delta. 25ms = 40fps
                        Thread.sleep(25);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    time2 = System.currentTimeMillis(); // Get current time
                    int delta = (int) (time2 - time1); // Calculate how long it's been since last update
                    update(delta); // Call update with our delta
                    time1 = time2; // Update our time variables.
                    if (rand.nextInt(10) == 5) {
                    	if (rand.nextInt(2) == 1) {
                    		balls.add(new Ball(rand.nextInt(400), 0, 0, rand.nextInt(20)+10));
                    	}
                    	else {
                    		balls.add(new Ball(0, rand.nextInt(800), rand.nextInt(20)+10, 0));
                    	}
                    }
                }
            }
        }.start(); // Start THREAD_B
    }
    
	public boolean onTouchEvent(MotionEvent motionEvent) {
		int newx = (int) motionEvent.getX();
		int newy = (int) motionEvent.getY();
		int movementSpeed = 5;
		if (player.getX() < newx) {
			player.setX(player.getX() + movementSpeed);
		}
		else if (player.getX() > newx) {
			player.setX(player.getX() - movementSpeed);
		}
		if (player.getY() < newy) {
			player.setY(player.getY() + movementSpeed);
		}
		else if (player.getY() > newy) {
			player.setY(player.getY() - movementSpeed);
		}
		this.invalidate();
		return true;
	}
	
    // Method that's called by the activity
    public void setActive(boolean active) {
        this.active = active;
    }
}
