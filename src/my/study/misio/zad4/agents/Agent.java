package my.study.misio.zad4.agents;

import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

import my.study.misio.zad4.agents.sensors.Sensor;
import my.study.misio.zad4.gui.IDrawable;

public abstract class Agent implements IDrawable {

	protected Point2D position;
	protected List<Sensor> sensors = new LinkedList<>();
	protected int v = 10;

	public Agent() {

	}

	public Point2D getPosition() {
		return position;
	}

	public void move(Action direction) {
		if (direction == Action.UP) {
			position.setLocation(position.getX(), position.getY() - v);
		} else if (direction == Action.DOWN) {
			position.setLocation(position.getX(), position.getY() + v);
		} else if (direction == Action.LEFT) {
			position.setLocation(position.getX() - v, position.getY());
		} else if (direction == Action.RIGHT) {
			position.setLocation(position.getX() + v, position.getY());
		}
		for (Sensor s : sensors) {
			s.update();
		}
	}
}
