package my.study.misio.zad4.filters;

import my.study.misio.zad4.agents.MoveDirection;
import my.study.misio.zad4.sensors.ISensorOwner;
import my.study.misio.zad4.sensors.Sensor;
import my.study.misio.zad4.env.Environment;
import my.study.misio.zad4.gui.IDrawable;

import java.util.LinkedList;
import java.util.List;

public abstract class Filter implements IDrawable, ISensorOwner {
    Environment env;
    List<Sensor> sensors = new LinkedList<>();

    Filter(Environment env) {
        this.env = env;
    }

    public abstract void run(List<Double> robotSensorsResult);

    public abstract void moveParticles(MoveDirection direction, double velocity);

    @Override
    public void addSensor(Sensor sensor) {
        sensors.add(sensor);
    }
}
