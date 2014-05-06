/**
 * 
 */
package my.study.misio.zad4;

import my.study.misio.zad4.agents.Agent;
import my.study.misio.zad4.agents.impl.BasicRobot;
import my.study.misio.zad4.env.Environment;
import my.study.misio.zad4.env.impl.FileEnviroment;
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
		final Environment env = new FileEnviroment("");
		final Agent agent = new BasicRobot(400, 400);
		final EnvironmentCanvas canvas = new EnvironmentCanvas(env);
		canvas.addDrawable(agent);
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new LostRobotWindow(canvas, env, agent).setVisible(true);
			}
		});

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					canvas.repaint();
				}
			}
		}).start();

	}
}
