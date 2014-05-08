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
	boolean hasMinDistance = false; // dla optymalizacji

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
		if (location.getX() == direction.getX()) { // pion
			location = (Point2D) newPos.clone();
			direction.setLocation(location.getX(), direction.getY());
		} else if (location.getY() == direction.getY()) { // poziom
			location = (Point2D) newPos.clone();
			direction.setLocation(direction.getX(), location.getY());
		}
		endPoint = direction;
	}

	@Override
	public double sens() {
		final Line2D.Double sensorLine = new Line2D.Double(location, direction);
		Iterator<List<Shape>> it = env.iterator(sensorLine);
		endPoint = (Point2D) direction.clone();
		double minDistance = location.distance(direction);
		hasMinDistance = false;

		for (; it.hasNext() && !hasMinDistance;) {
			List<Shape> shapes = it.next();
			for (Shape shape : shapes) {
				minDistance = getMinDistanceForShape(sensorLine, minDistance,
						shape);
			}
		}
		return minDistance;
	}

	private double getMinDistanceForShape(final Line2D sensorLine,
			double minDistance, Shape shape) {
		if (shape instanceof Line2D) {
			minDistance = getMinDistanceForLine(sensorLine, minDistance,
					(Line2D) shape);
		}
		return minDistance;
	}

	private double getMinDistanceForLine(final Line2D sensorLine,
			double minDistance, Line2D shape) {
		Point2D intersecPoint = null;
		if (shape.intersectsLine(sensorLine))
			if ((intersecPoint = Geometric.getIntersectionPoint(sensorLine,
					shape)) != null) {
				double d = location.distance(intersecPoint);
				minDistance = minDistance(minDistance, intersecPoint, d);
			}
		return minDistance;
	}

	private double minDistance(double minDistance, Point2D intersecPoint,
			double d) {
		if (d < minDistance) {
			endPoint = intersecPoint;
			minDistance = d;
			hasMinDistance = true;
		}
		return minDistance;
	}
}
