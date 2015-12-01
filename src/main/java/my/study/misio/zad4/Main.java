/**
 *
 */
package my.study.misio.zad4;

import my.study.misio.zad4.agents.*;
import my.study.misio.zad4.sensors.DistanceSensor;
import my.study.misio.zad4.sensors.Sensor;
import my.study.misio.zad4.values.*;
import my.study.misio.zad4.env.impl.ModifiableEnvironment;
import my.study.misio.zad4.filters.ParticipleFilter;
import my.study.misio.zad4.gui.EnvironmentCanvas;
import my.study.misio.zad4.gui.LostRobotWindow;
import my.study.misio.zad4.values.Error;

import javax.swing.*;
import java.awt.geom.Point2D;

/**
 * @author Patryk
 */
public class Main {

    public static void main(String[] args) {
        // final Environment env = new FileEnviroment("env1");
        final ModifiableEnvironment env = new ModifiableEnvironment();

        final EnvironmentCanvas canvas = new EnvironmentCanvas(env);
        env.setCanvas(canvas);

        Error errorCalculator = new SensorError();
        ValueCalculator calculator = new ValueCalculator(errorCalculator);
        final Sensor s1 = new DistanceSensor(env, new Point2D.Double(400, 400),
                new Point2D.Double(0, 400), calculator);
        final Sensor s2 = new DistanceSensor(env, new Point2D.Double(400, 400),
                new Point2D.Double(400, 0), calculator);
        final Sensor s3 = new DistanceSensor(env, new Point2D.Double(400, 400),
                new Point2D.Double(env.getWidth(), 400), calculator);
        final Sensor s4 = new DistanceSensor(env, new Point2D.Double(400, 400),
                new Point2D.Double(400, env.getHeight()), calculator);

        final ParticipleFilter filter = new ParticipleFilter(env, 1000, new ValueCalculator(new ParticipleError()));
        final Agent agent = new BasicRobot(filter, 400, 400);
        agent.addSensor(s1);
        agent.addSensor(s2);
        agent.addSensor(s3);
        agent.addSensor(s4);

        final Sensor s5 = new DistanceSensor(env, new Point2D.Double(400, 400),
                new Point2D.Double(0, 400), calculator);
        final Sensor s6 = new DistanceSensor(env, new Point2D.Double(400, 400),
                new Point2D.Double(400, 0), calculator);
        final Sensor s7 = new DistanceSensor(env, new Point2D.Double(400, 400),
                new Point2D.Double(env.getWidth(), 400), calculator);
        final Sensor s8 = new DistanceSensor(env, new Point2D.Double(400, 400),
                new Point2D.Double(400, env.getHeight()), calculator);

        filter.addSensor(s5);
        filter.addSensor(s6);
        filter.addSensor(s7);
        filter.addSensor(s8);

        canvas.addDrawable(filter);
        canvas.addDrawable(agent);

        java.awt.EventQueue.invokeLater(() -> {
            JFrame f = new LostRobotWindow(canvas, env, agent);
            f.setVisible(true);
        });

    }
}
