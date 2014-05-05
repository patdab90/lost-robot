package my.study.misio.zad4.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;

import javax.swing.JFrame;

import my.study.misio.zad4.env.Environment;

public class LostRobotWindow extends JFrame {

	private static final long serialVersionUID = 8955017289201366142L;
	private Environment enviroment;
	private Canvas canvas;

	public LostRobotWindow(Environment env) throws HeadlessException {
		super("Zagubiony robot");
		this.enviroment = env;

		this.setLayout(null);

		Container frameContainer = this.getContentPane();
		frameContainer.setPreferredSize(new Dimension(this.enviroment.getWidth(), this.enviroment.getHeight()));		
		
		canvas = new Canvas(this.getGraphicsConfiguration());
		canvas.setBackground(Color.RED);
		canvas.setSize(this.enviroment.getWidth(), this.enviroment.getHeight());
		
		this.add(canvas);
		
		this.pack();
	}
	
	

}
