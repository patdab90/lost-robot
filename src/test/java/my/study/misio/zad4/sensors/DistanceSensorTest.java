package my.study.misio.zad4.sensors;

import my.study.misio.zad4.agents.NoError;
import my.study.misio.zad4.env.TestEnvironment;
import my.study.misio.zad4.values.ValueCalculator;
import org.junit.Test;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import static org.junit.Assert.assertEquals;

/**
 * Created by patryk on 04.10.15.
 */
public class DistanceSensorTest {


    @Test
    public void testUpdatePositionForVerticalSensor() throws Exception {
        DistanceSensor sensor = new DistanceSensor(new TestEnvironment(20, 40, 40), new Point2D.Double(10, 10), new Point2D.Double(10, 40), new ValueCalculator(new NoError()));

        sensor.updatePosition(new Point2D.Double(15, 20));

        assertEquals(new Point2D.Double(15,20), sensor.location);
        assertEquals(new Point2D.Double(15, 40), sensor.endPoint);
    }


    @Test
    public void testUpdatePositionForHorizontalSensor() throws Exception {
        DistanceSensor sensor = new DistanceSensor(new TestEnvironment(20, 40, 40), new Point2D.Double(10, 10), new Point2D.Double(40, 10), new ValueCalculator(new NoError()));

        sensor.updatePosition(new Point2D.Double(15, 20));

        assertEquals(new Point2D.Double(15, 20), sensor.location);
        assertEquals(new Point2D.Double(40, 20), sensor.endPoint);
    }

    @Test
    public void testSensForEmptyBoardShouldReturnDistanceToEdge() throws Exception {
        DistanceSensor sensor = new DistanceSensor(new TestEnvironment(20, 40, 40), new Point2D.Double(10, 10), new Point2D.Double(40, 10), new ValueCalculator(new NoError()));

        double distance = sensor.sens();

        assertEquals(30.0, distance, 0.0);
    }

    @Test
    public void testSensForNotEmptyBoardShouldReturnDistanceElement() throws Exception {
        TestEnvironment env = new TestEnvironment(20, 40, 40);
        env.addObstacle(new Line2D.Double(30,5,30,20));
        DistanceSensor sensor = new DistanceSensor(env, new Point2D.Double(10, 10), new Point2D.Double(40, 10), new ValueCalculator(new NoError()));

        double distance = sensor.sens();

        assertEquals(20.0, distance, 0.0);
    }

    @Test
    public void testSensForNotEmptyBoardShouldReturnDistanceToEdge() throws Exception {
        TestEnvironment env = new TestEnvironment(20, 40, 40);
        env.addObstacle(new Line2D.Double(30,5,30,7));
        DistanceSensor sensor = new DistanceSensor(env, new Point2D.Double(10, 10), new Point2D.Double(40, 10), new ValueCalculator(new NoError()));

        double distance = sensor.sens();

        assertEquals(30.0, distance, 0.0);
    }

    @Test
    public void testSensForNotEmptyBoardShouldReturnDistanceElement2() throws Exception {
        TestEnvironment env = new TestEnvironment(20, 40, 40);
        env.addObstacle(new Rectangle2D.Double(30,5,5,20));
        DistanceSensor sensor = new DistanceSensor(env, new Point2D.Double(10, 10), new Point2D.Double(40, 10), new ValueCalculator(new NoError()));

        double distance = sensor.sens();

        assertEquals(20.0, distance, 0.0);
    }
}