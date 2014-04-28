package edu.purdue.cs.dodgeball;

public class Velocity {
	private int x;
	private int y;
	
	public Velocity(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int xspeed() {
		return x;
	}
	
	public int yspeed() {
		return y;
	}
}
