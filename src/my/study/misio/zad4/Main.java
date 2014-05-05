/**
 * 
 */
package my.study.misio.zad4;

import my.study.misio.zad4.env.impl.FileEnviroment;
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
		new LostRobotWindow(new FileEnviroment(900, 900)).setVisible(true);
	}

}
