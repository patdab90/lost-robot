package my.study.misio.zad4.agents.impl;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import my.study.misio.zad4.agents.Agent;

public class BasicRobot extends Agent {

	public BasicRobot(int posX, int posY) {
		super();
		this.position = new Point2D.Double(posX, posY);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.ORANGE);
		g.fillOval((int) position.getX(), (int) position.getY(), 15, 15);
	}

}
