package my.study.misio.zad4.agents;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.List;

import my.study.misio.zad4.Geometric;
import my.study.misio.zad4.env.Environment;

public class DistanceSensor extends Sensor {

	private Point2D direction;
	private Point2D endPoint;

	public DistanceSensor(Environment e, Point2D a, Point2D direction) {
		super(e, a);
		this.direction = (Point2D) direction.clone();
		endPoint = (Point2D) direction.clone();
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.draw(new Line2D.Double(location, endPoint));
	}

	@Override
	public void updatePosition(Point2D newPos, double rotation) {
		// rotation nie jest u¿ywane narazie
		System.out.println(location + " Before: " + direction);
		if (location.getX() == direction.getX()) { // pion
			location = (Point2D) newPos.clone();
			direction.setLocation(location.getX(), direction.getY());
		} else if (location.getY() == direction.getY()) { // poziom
			location = (Point2D) newPos.clone();
			direction.setLocation(direction.getX(), location.getY());
		}
		endPoint = direction;
		System.out.println(location + " After: " + direction);
	}

	@Override
	public double sens() {

		Iterator<List<Shape>> it = env.iterator(new Line2D.Double(location,
				direction));
		List<Shape> shapes = null;// env.getNearestObstacleOnLine(new
									// Line2D.Double(
		// location, direction));
		if (!it.hasNext()) {
			endPoint = (Point2D) direction.clone();
			return -1.0;
		}
		shapes = it.next();

		double distance = 100000000.0;
		Point2D p = null;
		for (Shape s : shapes) {
			if (s instanceof Line2D) {
				if (((Line2D) s).intersectsLine(new Line2D.Double(location,
						direction))) {

					p = Geometric.getIntersectionPoint(new Line2D.Double(
							location, direction), (Line2D) s);
					if (p != null) {
						double d = p.distance(location);
						if (d < distance) {
							endPoint = p;
							distance = d;
						}
					}
				}
			} else {
				Point2D[] pl = Geometric.getIntersectionPoint(new Line2D.Double(
						location, direction), (Rectangle2D) s);
				for (Point2D p2 : pl) {
					if (p2 != null) {
						double d = p2.distance(location);
						if (d < distance) {
							endPoint = p2;
							p = p2;
							distance = d;
						}
					}
				}
			}
		}
		if (p == null) {
			endPoint = (Point2D) direction.clone();
			return -1.0;
		}
		return distance;
	}
}
