package my.study.misio.zad4.filters;

import java.awt.geom.Point2D;

public class Particle {
	private Point2D position;
	private double weight;
	
	public Particle(Point2D p){
		setPosition(p);
		setWeight(0);
	}

	public Point2D getPosition() {
		return position;
	}

	public void setPosition(Point2D position) {
		this.position = position;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
}
