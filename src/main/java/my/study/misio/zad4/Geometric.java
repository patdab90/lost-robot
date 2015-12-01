package my.study.misio.zad4;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.HashSet;
import java.util.Set;

public final class Geometric {

    static public Set<Point2D> getIntersectionPoint(Line2D line,
                                                    Rectangle2D rectangle) {
        Set<Point2D> p = new HashSet<>();

        // Top line
        Point2D point = getIntersectionPoint(line, new Line2D.Double(rectangle.getX(),
                rectangle.getY(), rectangle.getX() + rectangle.getWidth(),
                rectangle.getY()));
        if (point != null) {
            p.add(point);
        }
        // Bottom line
        point = getIntersectionPoint(
                line,
                new Line2D.Double(rectangle.getX(), rectangle.getY()
                        + rectangle.getHeight(), rectangle.getX()
                        + rectangle.getWidth(), rectangle.getY()
                        + rectangle.getHeight()));
        if (point != null) {
            p.add(point);
        }
        // Left side...
        point = getIntersectionPoint(line, new Line2D.Double(rectangle.getX(),
                rectangle.getY(), rectangle.getX(), rectangle.getY()
                + rectangle.getHeight()));
        if (point != null) {
            p.add(point);
        }
        // Right side
        point = getIntersectionPoint(
                line,
                new Line2D.Double(rectangle.getX() + rectangle.getWidth(),
                        rectangle.getY(), rectangle.getX()
                        + rectangle.getWidth(), rectangle.getY()
                        + rectangle.getHeight()));
        if (point != null) {
            p.add(point);
        }
        return p;

    }

    static public Point2D getIntersectionPoint(Line2D lineA, Line2D lineB) {
        if (lineA.intersectsLine(lineB)) {
            return calcIntersectionPoint(lineA, lineB);
        }
        return null;
    }

    private static Point2D calcIntersectionPoint(Line2D lineA, Line2D lineB) {
        double x1 = lineA.getX1();
        double y1 = lineA.getY1();
        double x2 = lineA.getX2();
        double y2 = lineA.getY2();

        double x3 = lineB.getX1();
        double y3 = lineB.getY1();
        double x4 = lineB.getX2();
        double y4 = lineB.getY2();


        double d = delta(x1, y1, x2, y2, x3, y3, x4, y4);
        if (d != 0) {
            double xi = intersectionAxisValue(x1, x2, x3, x4, d, x3 * y4, x1 * y2, y3 * x4, y1 * x2);
            double yi = intersectionAxisValue(y1, y2, y3, y4, d, x3 * y4, x1 * y2, y3 * x4, y1 * x2);
            return new Point2D.Double(xi, yi);
        }
        return null;
    }

    private static double intersectionAxisValue(double x1, double x2, double x3, double x4, double d, double x32, double y32, double x42, double y42) {
        return delta(x3, x1, x4, x2, x32, y32, x42, y42) / d;
    }

    private static double delta(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        return (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
    }

}
