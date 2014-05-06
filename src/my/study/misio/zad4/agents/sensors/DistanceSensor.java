package my.study.misio.zad4.agents.sensors;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;

import my.study.misio.zad4.agents.Agent;
import my.study.misio.zad4.env.Environment;

public class DistanceSensor extends Sensor {
	private Point2D direction;
	private Point2D endPoint;

	public DistanceSensor(Agent agent, Environment env) {
		super(agent, env);
		direction = new Double();
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.draw(new Line2D.Double(agent.getPosition(), endPoint));
	}

	@Override
	public double getMeasure() {
		// TODO Auto-generated method stub
		return 0.0;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
