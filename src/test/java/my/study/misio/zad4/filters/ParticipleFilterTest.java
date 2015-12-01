package my.study.misio.zad4.filters;

import my.study.misio.zad4.sensors.DistanceSensor;
import my.study.misio.zad4.agents.MoveDirection;
import my.study.misio.zad4.agents.NoError;
import my.study.misio.zad4.sensors.Sensor;
import my.study.misio.zad4.env.TestEnvironment;
import my.study.misio.zad4.values.ValueCalculator;
import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by patryk on 04.10.15.
 */
public class ParticipleFilterTest {

    @Test
    public void testRun() throws Exception {
        ParticipleFilter filter = setUpFilter();
        List<Double> results = new ArrayList<>();
        results.add(20.0);
        results.add(20.0);
        results.add(20.0);
        results.add(20.0);

        filter.run(results);

        List<Particle> participles = filter.getParticles();
        assertNotNull(participles);
        assertEquals(3, participles.size());
        assertNotEquals(0, participles.get(0).getWeight());
//        for (Particle particle: participles) {
//            assertNotEquals(0, particle.getWeight());
//        }
    }

    private ParticipleFilter setUpFilter() {
        Particle particle1 = new Particle(new Point2D.Double(5, 5));
        Particle particle2 = new Particle(new Point2D.Double(10, 10));
        Particle particle3 = new Particle(new Point2D.Double(15, 15));
        List<Particle> list = new ArrayList<>();
        list.add(particle1);
        list.add(particle2);
        list.add(particle3);

        TestEnvironment env = new TestEnvironment(20, 40, 40);

        Sensor s1 = new DistanceSensor(env, new Point2D.Double(40, 40),
                new Point2D.Double(0, 40), new ValueCalculator(new NoError()));
        Sensor s2 = new DistanceSensor(env, new Point2D.Double(40, 40),
                new Point2D.Double(40, 0), new ValueCalculator(new NoError()));
        Sensor s3 = new DistanceSensor(env, new Point2D.Double(40, 40),
                new Point2D.Double(env.getWidth(), 40), new ValueCalculator(new NoError()));
        Sensor s4 = new DistanceSensor(env, new Point2D.Double(40, 40),
                new Point2D.Double(40, env.getHeight()), new ValueCalculator(new NoError()));

        ParticipleFilter filter = new ParticipleFilter(env, new ValueCalculator(new NoError()), list);
        filter.addSensor(s1);
        filter.addSensor(s2);
        filter.addSensor(s4);
        filter.addSensor(s3);
        return filter;
    }

    @Test
    public void testMoveParticlesWithOneParticiple() throws Exception {
        Particle particle1 = new Particle(new Point2D.Double(10, 10));
        List<Particle> list = new ArrayList<>();
        list.add(particle1);
        ParticipleFilter filter = new ParticipleFilter(new TestEnvironment(20, 40, 40), new ValueCalculator(new NoError()), list);

        filter.moveParticles(MoveDirection.RIGHT, 15);

        assertEquals(new Point2D.Double(25, 10), filter.getParticles().get(0).getPosition());
    }
}