package my.study.misio.zad4.sensors;

import my.study.misio.zad4.env.Environment;
import my.study.misio.zad4.values.ValueCalculator;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.*;
import java.util.List;

/**
 * Created by patryk on 04.10.15.
 */
public class ObstacleSensor extends Sensor {

    private final boolean horizontal;
    private final Point2D direction;
    private final ValueCalculator valueCalculator;

    public ObstacleSensor(Environment e, Point2D a, Point2D direction, ValueCalculator valueCalculator) {
        super(e, a);
        this.direction = direction;
        this.valueCalculator = valueCalculator;
        horizontal = location.getX() == direction.getX();
    }

    @Override
    public double sens() {
        final Line2D.Double sensorLine = new Line2D.Double(location, direction);
        Iterator<java.util.List<Shape>> it = env.iterator(sensorLine);
        for (; it.hasNext();) {
            List<Shape> shapes = it.next();
            for (Shape shape: shapes) {
                if(shape instanceof Line2D){
                    if (((Line2D)shape).intersectsLine(sensorLine)) return valueCalculator.value(1.0);
                }else if(shape instanceof Rectangle2D){
                    if (((Rectangle2D)shape).intersectsLine(sensorLine)) return valueCalculator.value(1.0);
                }
            }
        }
        return valueCalculator.value(0.0);
    }

    @Override
    public void updatePosition(Point2D newPos) {
        if (horizontal) { // pion
            updateLocationAndDirection(newPos, newPos.getX(), direction.getY());
        } else if (location.getY() == direction.getY()) { // poziom
            updateLocationAndDirection(newPos, direction.getX(), newPos.getY());
        }
    }

    private void updateLocationAndDirection(Point2D newPos, double x, double y) {
        location = (Point2D) newPos.clone();
        direction.setLocation(x, y);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.BLUE);
        g.draw(new Line2D.Double(location, direction));
    }
}
