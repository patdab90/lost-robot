package my.study.misio.zad4.agents;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

import my.study.misio.zad4.filters.Filter;
import my.study.misio.zad4.gui.IDrawable;

public abstract class Agent implements IDrawable, ISensorOwner {

	protected Point2D position;

	protected int v = 10;
	protected Filter filter;
	protected List<Sensor> sensors = new LinkedList<>();

	public Agent(Filter f) {
		filter = f;
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
		position.setLocation(position.getX() + direction.getCoords().getX()*v, position.getY() + direction.getCoords().getY()*v);
		
		filter.moveParticles(direction.getCoords().getX()*v, direction.getCoords().getY()*v);
		
		List<Double> sResult = new LinkedList<>();
		for (Sensor s : sensors) {
			s.updatePosition(position, 0);
			sResult.add(s.sens());
		}
		filter.run(sResult);
	}
}
