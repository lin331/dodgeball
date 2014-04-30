package edu.purdue.cs.dodgeball;

public class Ball {
	private float x;
	private float y;
	private Velocity v;
	
	public Ball(float x, float y, float xspeed, float yspeed) {
		this.x = x;
		this.y = y;
		this.v = new Velocity(xspeed,yspeed);
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setx(float x) {
		this.x = x;
	}
	
	public void sety(float y) {
		this.y = y;
	}
	
	public Velocity getV() {
		return v;
	}

	public boolean hit(Player p) {
		if (Math.hypot((double) x-p.getX(), (double) y-p.getY()) < 50) {
			return true;
		}
		return false;
	}
}
