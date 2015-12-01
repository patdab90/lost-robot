package my.study.misio.zad4.agents;

import java.awt.geom.Point2D;
import java.security.InvalidParameterException;

public enum MoveDirection {
    UP(new Point2D.Double(0, -1)), DOWN(new Point2D.Double(0, 1)), LEFT(new Point2D.Double(-1, 0)), RIGHT(new Point2D.Double(1, 0));

    private final Point2D coordinates;

    MoveDirection(Point2D c) {
        coordinates = c;
    }

    private Point2D getCoordinates() {
        return coordinates;
    }

    public double movementX(double velocity){
        return getCoordinates().getX() * velocity;
    }
    public double movementY(double velocity){
        return getCoordinates().getY() * velocity;
    }
    public double increaseX(double xValue){return xValue+coordinates.getX(); }
    public double increaseY(double yValue){return yValue+coordinates.getY(); }
    public static MoveDirection direction(double x1, double y1, double x2, double y2){
        if (x1 == x2) { // pion
            if (y1 > y2) {
                return MoveDirection.UP;
            } else {
                return MoveDirection.DOWN;
            }
        } else if (y1 == y2) { // poziom
            if (x1 > x2) {
                return MoveDirection.LEFT;
            } else {
                return MoveDirection.RIGHT;
            }
        } else {
            throw new InvalidParameterException();
        }
    }
}
