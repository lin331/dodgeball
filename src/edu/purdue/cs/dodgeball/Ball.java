package edu.purdue.cs.dodgeball;

public class Ball {
	private int x;
	private int y;
	private Velocity v;
	
	public Ball(int x, int y, int xspeed, int yspeed) {
		this.x = x;
		this.y = y;
		this.v = new Velocity(xspeed,yspeed);
	}
	
	public int xcoord() {
		return x;
	}
	
	public int ycoord() {
		return y;
	}
	
	public void setx(int x) {
		this.x = x;
	}
	
	public void sety(int y) {
		this.y = y;
	}
	
	public Velocity vel() {
		return v;
	}
}
