/**
 * 
 */
package my.study.misio.zad4;

import java.awt.geom.Point2D;

import my.study.misio.zad4.agents.Agent;
import my.study.misio.zad4.agents.BasicRobot;
import my.study.misio.zad4.agents.DistanceSensor;
import my.study.misio.zad4.agents.Sensor;
import my.study.misio.zad4.env.Environment;
import my.study.misio.zad4.env.impl.FileEnviroment;
import my.study.misio.zad4.filters.Filter;
import my.study.misio.zad4.filters.ParticipleFilter;
import my.study.misio.zad4.gui.EnvironmentCanvas;
import my.study.misio.zad4.gui.LostRobotWindow;

/**
 * @author Patryk
 * 
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final Environment env = new FileEnviroment("env1");
		final Agent agent = new BasicRobot(400, 400);
		final EnvironmentCanvas canvas = new EnvironmentCanvas(env);
		final Sensor s1 = new DistanceSensor(env, new Point2D.Double(400, 400),
				new Point2D.Double(0, 400));
		final Sensor s2 = new DistanceSensor(env, new Point2D.Double(400, 400),
				new Point2D.Double(400, 0));
		final Sensor s3 = new DistanceSensor(env, new Point2D.Double(400, 400),
				new Point2D.Double(env.getWidth(), 400));
		final Sensor s4 = new DistanceSensor(env, new Point2D.Double(400, 400),
				new Point2D.Double(400, env.getHeight()));

		agent.addSensor(s1);
		agent.addSensor(s2);
		agent.addSensor(s3);
		agent.addSensor(s4);

		final Filter filter = new ParticipleFilter(env);

		final Sensor s5 = new DistanceSensor(env, new Point2D.Double(400, 400),
				new Point2D.Double(0, 400));
		final Sensor s6 = new DistanceSensor(env, new Point2D.Double(400, 400),
				new Point2D.Double(400, 0));
		final Sensor s7 = new DistanceSensor(env, new Point2D.Double(400, 400),
				new Point2D.Double(env.getWidth(), 400));
		final Sensor s8 = new DistanceSensor(env, new Point2D.Double(400, 400),
				new Point2D.Double(400, env.getHeight()));

		filter.addSensor(s5);
		filter.addSensor(s6);
		filter.addSensor(s7);
		filter.addSensor(s8);

		canvas.addDrawable(filter);
		canvas.addDrawable(agent);
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new LostRobotWindow(canvas, env, agent).setVisible(true);
			}
		});

		// new Thread(new Runnable() {
		// @Override
		// public void run() {
		// while (true) {
		// canvas.repaint();
		// }
		// }
		// }).start();

	}
}
