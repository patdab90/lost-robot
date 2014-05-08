package my.study.misio.zad4.agents;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import my.study.misio.zad4.env.Environment;
import my.study.misio.zad4.gui.IDrawable;

public abstract class Sensor implements IDrawable {

	final protected Environment env;
	protected Point2D location;

	public Sensor(Environment e, Point2D a) {
		this.env = e;
		this.location = a;
	}

	public abstract double sens();

	public abstract void updatePosition(Point2D newPos, double rotation);
}
