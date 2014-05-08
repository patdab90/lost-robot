package my.study.misio.zad4.filters;

import java.util.List;

import my.study.misio.zad4.agents.ISensorOwner;
import my.study.misio.zad4.agents.Sensor;
import my.study.misio.zad4.env.Environment;
import my.study.misio.zad4.gui.IDrawable;

public abstract class Filter implements IDrawable, ISensorOwner {
	protected Environment env;
	protected List<Sensor> sensors;

	public Filter(Environment env) {
		this.env = env;
	}

	@Override
	public void addSensor(Sensor sensor) {
		sensors.add(sensor);
	}
}
