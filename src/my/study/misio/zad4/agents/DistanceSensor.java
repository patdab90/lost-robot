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
import my.study.misio.zad4.RandomSingleton;
import my.study.misio.zad4.env.Environment;

public class DistanceSensor extends Sensor {

	private Point2D direction;
	private Point2D endPoint;
	private boolean horizontal = false;
	boolean hasMinDistance = false; // dla optymalizacji

	public DistanceSensor(Environment e, Point2D a, Point2D direction) {
		super(e, a);
		this.direction = (Point2D) direction.clone();
		endPoint = (Point2D) direction.clone();
		horizontal = location.getX() == direction.getX();
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.draw(new Line2D.Double(location, endPoint));
	}

	@Override
	public void updatePosition(Point2D newPos, double rotation) {
		// rotation nie jest u¿ywane narazie
		if (horizontal) { // pion
			location = (Point2D) newPos.clone();
			direction.setLocation(location.getX(), direction.getY());
		} else if (location.getY() == direction.getY()) { // poziom
			location = (Point2D) newPos.clone();
			direction.setLocation(direction.getX(), location.getY());
		}
		endPoint = direction;
	}

	private double getSensorError() {
		return (RandomSingleton.getInstance().nextInt(2) - 1)
				* RandomSingleton.getInstance().nextGaussian() * 15;
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
					((Line2D) shape));
		} else {
			minDistance = getMinDistanceForRect(sensorLine, minDistance,
					shape.getBounds2D());
		}
		return minDistance;
	}

	private double getMinDistanceForLine(final Line2D sensorLine,
			double minDistance, Line2D shape) {
		Point2D intersecPoint = null;
		if (shape.intersectsLine(sensorLine))
			if ((intersecPoint = Geometric.getIntersectionPoint(sensorLine,
					shape)) != null) {
				// doliczamy b³ad
				intersecPoint.setLocation(intersecPoint.getX()
						+ (horizontal ? 0 : getSensorError()),
						intersecPoint.getY()
								+ (horizontal ? getSensorError() : 0));
				double d = location.distance(intersecPoint);
				minDistance = minDistance(minDistance, intersecPoint, d);
			}
		return minDistance;
	}

	private double getMinDistanceForRect(final Line2D sensorLine,
			double minDistance, Rectangle2D shape) {
		Point2D[] intersecPoints = null;
		if (shape.intersectsLine(sensorLine)) {
			intersecPoints = Geometric.getIntersectionPoint(sensorLine, shape);
			for (Point2D point2d : intersecPoints) {
				if (point2d == null)
					continue;
				// doliczanie b³edu
				point2d.setLocation(point2d.getX()
						+ (horizontal ? 0 : getSensorError()), point2d.getY()
						+ (horizontal ? getSensorError() : 0));
				double d = location.distance(point2d);
				minDistance = minDistance(minDistance, point2d, d);
			}
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
