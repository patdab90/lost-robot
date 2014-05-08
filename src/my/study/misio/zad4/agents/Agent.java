package my.study.misio.zad4.agents;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

import my.study.misio.zad4.gui.IDrawable;

public abstract class Agent implements IDrawable, ISensorOwner {

	protected Point2D position;

	protected int v = 10;

	protected List<Sensor> sensors = new LinkedList<>();

	public Agent() {

	}

	@Override
	public void addSensor(Sensor sensor) {
		sensors.add(sensor);
	}

	@Override
	public void draw(Graphics2D g) {
		for (Sensor s : sensors) {
			s.draw(g);
		}
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
			s.updatePosition(position, 0);
			s.sens();
		}
	}
}
