package my.study.misio.zad4.env;

import org.junit.Test;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by patryk on 03.10.15.
 */
public class EnvironmentTest {
    @Test
    public void testCreateGraduationProportionalSizeShouldCreate4Graduations() throws Exception {
        Environment env = new TestEnvironment(20, 40, 40);

        assertNotNull(env.graduation);
        assertEquals(2, env.graduation.length);
        assertEquals(2, env.graduation[0].length);
        assertEquals(2, env.graduation[1].length);
    }

    @Test
    public void testCreateGraduationNotProportionalSizeShouldCreate6Graduations() throws Exception {
        Environment env = new TestEnvironment(20, 40, 50);

        assertNotNull(env.graduation);
        assertEquals(2, env.graduation.length);
        assertEquals(3, env.graduation[0].length);
        assertEquals(3, env.graduation[1].length);
    }

    @Test
    public void testAddObstacleAddSmallRectangleShouldAddToOneCell() throws Exception {
        Environment env = new TestEnvironment(20, 40, 40);
        Rectangle2D.Double rec = new Rectangle2D.Double(0.0, 0.0, 10.0, 10.0);

        env.addObstacle(rec);

        assertNotNull(env.graduation[0][0].cell);
        assertEquals(0, env.graduation[0][1].cell.size());
        assertEquals(0, env.graduation[1][0].cell.size());
        assertEquals(0, env.graduation[1][1].cell.size());
        assertEquals(1, env.graduation[0][0].cell.size());
        assertEquals(rec, env.graduation[0][0].cell.get(0));
    }

    @Test
    public void testAddObstacleAddBigRectangleShouldAddToTwoCell() throws Exception {
        Environment env = new TestEnvironment(20, 40, 40);
        Rectangle2D.Double rec = new Rectangle2D.Double(0.0, 0.0, 30.0, 10.0);

        env.addObstacle(rec);

        assertNotNull(env.graduation[0][0]);
        assertEquals(0, env.graduation[0][1].cell.size());
        assertEquals(1, env.graduation[1][0].cell.size());
        assertEquals(0, env.graduation[1][1].cell.size());
        assertEquals(1, env.graduation[0][0].cell.size());
        assertEquals(rec, env.graduation[0][0].cell.get(0));
        assertEquals(rec, env.graduation[1][0].cell.get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateIteratorShouldThrowException() throws Exception {
        Environment env = new TestEnvironment(20, 40, 40);

        env.iterator(new Line2D.Double(0.0, 0.0, 1.0, 1.0));
    }

    @Test
    public void testPointOutOfEnvironmentShouldReturnFalse() throws Exception {
        Environment env = new TestEnvironment(20, 40, 40);

        assertFalse(env.pointOutOfEnvironment(10, 10));
        assertFalse(env.pointOutOfEnvironment(39, 39));
        assertFalse(env.pointOutOfEnvironment(0, 0));
    }

    @Test
    public void testPointOutOfEnvironmentShouldReturnTrue() throws Exception {
        Environment env = new TestEnvironment(20, 40, 40);

        assertTrue(env.pointOutOfEnvironment(-10, 10));
        assertTrue(env.pointOutOfEnvironment(-10, -10));
        assertTrue(env.pointOutOfEnvironment(10, -10));
        assertTrue(env.pointOutOfEnvironment(40, 40));
        assertTrue(env.pointOutOfEnvironment(0, 45));
        assertTrue(env.pointOutOfEnvironment(45, 45));
        assertTrue(env.pointOutOfEnvironment(45, 0));
    }

    @Test
    public void testIterator() {
        Environment env = new TestEnvironment(20, 40, 40);

        Iterator<Shape> it = env.shapeIterator(new Line2D.Double(10, 10, 40, 10));

        assertNotNull(it);
        assertFalse(it.hasNext());
    }


    @Test
    public void testIteratorWithOneElementCollection() {
        TestEnvironment env = new TestEnvironment(20, 40, 40);
        Line2D line = new Line2D.Double(15, 5, 15, 30);
        env.addObstacle(line);

        Iterator<Shape> it = env.shapeIterator(new Line2D.Double(10, 10, 40, 10));

        assertNotNull(it);
        assertTrue(it.hasNext());
        assertEquals(line, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    public void testIteratorWithTwoElementCollection() {
        TestEnvironment env = new TestEnvironment(20, 40, 40);
        Line2D line1 = new Line2D.Double(15, 5, 15, 30);
        env.addObstacle(line1);
        Line2D line2 = new Line2D.Double(20, 5, 20, 30);
        env.addObstacle(line2);

        Iterator<Shape> it = env.shapeIterator(new Line2D.Double(10, 10, 40, 10));

        assertNotNull(it);
        assertTrue(it.hasNext());
        assertEquals(line1, it.next());
        assertTrue(it.hasNext());
        assertEquals(line2, it.next());
        assertFalse(it.hasNext());
    }

    @Test
    public void testIteratorWithTwoElementCollectionNotOnGrid() {
        TestEnvironment env = new TestEnvironment(20, 40, 40);
        Line2D line1 = new Line2D.Double(5, 5, 5, 30);
        env.addObstacle(line1);
        Line2D line2 = new Line2D.Double(5, 25, 25, 25);
        env.addObstacle(line2);

        Iterator<Shape> it = env.shapeIterator(new Line2D.Double(25, 25, 40, 25));

        assertNotNull(it);
        assertTrue(it.hasNext());
        assertEquals(line2, it.next());
        assertFalse(it.hasNext());
    }
}
