package my.study.misio.zad4;

import org.junit.Test;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by patryk on 02.10.15.
 */

public class GeometricTest {

    @Test
    public void testGetIntersectionPointWithEmptyLinesShouldReturnNull() {
        Line2D lineA = new Line2D.Double();
        Line2D lineB = new Line2D.Double();

        Point2D point = Geometric.getIntersectionPoint(lineA, lineB);
        assertNull(point);
    }

    @Test
    public void testGetIntersectionPointWithLinesShouldReturnPoint() {
        Line2D lineA = new Line2D.Double(0.0, 0.0, 10.0, 10.0);
        Line2D lineB = new Line2D.Double(0.0, 10.0, 10.0, 0.0);

        Point2D point = Geometric.getIntersectionPoint(lineA, lineB);
        assertNotNull(point);
        assertEquals(new Point2D.Double(5.0, 5.0), point);
    }

    @Test
    public void testGetIntersectionPointNotCrossingLinesShouldReturnNull() {
        Line2D lineA = new Line2D.Double(0.0, 0.0, 10.0, 10.0);
        Line2D lineB = new Line2D.Double(2.0, 3.0, 2.0, 8.0);

        Point2D point = Geometric.getIntersectionPoint(lineA, lineB);
        assertFalse(lineA.intersectsLine(lineB));
        assertNull(point);
    }

    @Test
    public void testGetIntersectionPointWithEmptyRectangleShouldReturnEmpty() {
        Rectangle2D rectangle = new Rectangle2D.Double();
        Line2D lineA = new Line2D.Double(0.0, 0.0, 10.0, 10.0);

        Set<Point2D> points = Geometric.getIntersectionPoint(lineA, rectangle);

        assertNotNull(points);
        assertTrue(points.isEmpty());
    }

    @Test
    public void testGetIntersectionPointRectangleShouldReturnTwoPoints() {
        Rectangle2D rectangle = new Rectangle2D.Double(2.0, 3.0, 5.0, 5.0);
        Line2D lineA = new Line2D.Double(0.0, 0.0, 10.0, 10.0);

        Set<Point2D> points = Geometric.getIntersectionPoint(lineA, rectangle);

        assertNotNull(points);
        assertEquals(2, points.size());
        assertTrue(points.contains(new Point2D.Double(3.0, 3.0)));
        assertTrue(points.contains(new Point2D.Double(7.0, 7.0)));
    }
}
