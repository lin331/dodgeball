package edu.purdue.cs.dodgeball;

public class Velocity {
	private float x;
	private float y;
	
	public Velocity(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
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
