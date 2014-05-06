package my.study.misio.zad4.agents;

import java.awt.geom.Point2D;

import my.study.misio.zad4.gui.IDrawable;

public abstract class Agent implements IDrawable {

	protected Point2D position;

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
		} else if (direction == Action.LEFT){
			position.setLocation(position.getX() - v, position.getY());
		} else if (direction == Action.RIGHT){
			position.setLocation(position.getX() + v, position.getY());
		}
	}
}
