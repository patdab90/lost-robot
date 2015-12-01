package my.study.misio.zad4.sensors;

import my.study.misio.zad4.Geometric;
import my.study.misio.zad4.env.Environment;
import my.study.misio.zad4.values.ValueCalculator;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class DistanceSensor extends Sensor {

    private final ValueCalculator valueCalculator;
    private final Point2D direction;
    protected Point2D endPoint;
    private boolean hasMinDistance = false; // dla optymalizacji
    private boolean horizontal = false;

    public DistanceSensor(Environment e, Point2D a, Point2D direction, ValueCalculator valueCalculator) {
        super(e, a);
        this.valueCalculator = valueCalculator;
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
    public void updatePosition(Point2D newPos) {
        if (horizontal) { // pion
            updateLocationAndDirection(newPos, newPos.getX(), direction.getY());
        } else if (location.getY() == direction.getY()) { // poziom
            updateLocationAndDirection(newPos, direction.getX(), newPos.getY());
        }
        endPoint = direction;
    }

    private void updateLocationAndDirection(Point2D newPos, double x, double y) {
        location = (Point2D) newPos.clone();
        direction.setLocation(x, y);
    }

    @Override
    public double sens() {
        final Line2D.Double sensorLine = new Line2D.Double(location, direction);
        endPoint = (Point2D) direction.clone();
        double minDistance = location.distance(direction);
        hasMinDistance = false;

        Iterator<List<Shape>> it = env.iterator(sensorLine);
        for (; it.hasNext() && !hasMinDistance; ) {
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
        Point2D intersectPoint;
        if (shape.intersectsLine(sensorLine))
            if ((intersectPoint = Geometric.getIntersectionPoint(sensorLine,
                    shape)) != null) {
                minDistance = getMinDistance(minDistance, intersectPoint);
            }
        return minDistance;
    }

    private double getMinDistance(double minDistance, Point2D intersectPoint) {
        intersectPoint.setLocation(valueCalculator.value(intersectPoint.getX()),
                valueCalculator.value(intersectPoint.getY()));
        double d = location.distance(intersectPoint);
        if (d < minDistance) {
            endPoint = intersectPoint;
            minDistance = d;
            hasMinDistance = true;
        }
        return minDistance;
    }

    private double getMinDistanceForRect(final Line2D sensorLine,
                                         double minDistance, Rectangle2D shape) {
        Set<Point2D> intersectPoints;
        if (shape.intersectsLine(sensorLine)) {
            intersectPoints = Geometric.getIntersectionPoint(sensorLine, shape);
            for (Point2D intersectPoint : intersectPoints) {
                minDistance = getMinDistance(minDistance, intersectPoint);
            }
        }
        return minDistance;
    }

}
