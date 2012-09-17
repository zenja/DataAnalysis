package org.zenja.dataanalysis.datatype;

public class TwoDimensionalPoint {
	private double x;
	private double y;
	
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public TwoDimensionalPoint() {
		x = 0;
		y = 0;
	}
	
	public TwoDimensionalPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}
}
