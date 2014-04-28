package edu.purdue.cs.dodgeball;

public class Ball implements Comparable<Ball> {
	private int x;
	private int y;
	private Velocity v;
	
	public Ball(int x, int y, int xspeed, int yspeed) {
		this.x = x;
		this.y = y;
		this.v = new Velocity(xspeed,yspeed);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setx(int x) {
		this.x = x;
	}
	
	public void sety(int y) {
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
	
	@Override
	public int compareTo(Ball b) {
		if (b.getX() != x) {
			return 0;
		}
		if (b.getY() != y) {
			return 0;
		}
		if (!b.getV().equals(v)) {
			return 0;
		}
		return 1;
	}
}
