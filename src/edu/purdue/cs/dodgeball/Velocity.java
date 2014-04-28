package edu.purdue.cs.dodgeball;

public class Velocity {
	private int x;
	private int y;
	
	public Velocity(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean equals(Velocity v) {
		if (v.getX() != x) {
			return false;
		}
		else if (v.getY() != y) {
			return false;
		}
		return true;
	}
}
