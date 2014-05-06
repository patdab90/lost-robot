package my.study.misio.zad4.agents.sensors;

import my.study.misio.zad4.agents.Agent;
import my.study.misio.zad4.env.Environment;
import my.study.misio.zad4.gui.IDrawable;

public abstract class Sensor implements IDrawable {
	protected Agent agent;
	protected Environment env;

	public Sensor(Agent agent, Environment env) {
		super();
		this.agent = agent;
		this.env = env;
	}

	public abstract double getMeasure();
	
	public abstract void update();
}
