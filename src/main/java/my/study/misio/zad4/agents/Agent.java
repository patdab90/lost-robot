package my.study.misio.zad4.agents;

import my.study.misio.zad4.sensors.ISensorOwner;
import my.study.misio.zad4.filters.Filter;
import my.study.misio.zad4.gui.IDrawable;
import my.study.misio.zad4.sensors.Sensor;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

public abstract class Agent implements IDrawable, ISensorOwner {

    Point2D position;

    private final Filter filter;
    private final List<Sensor> sensors = new LinkedList<>();

    Agent(Filter f) {
        filter = f;
    }

    public void addSensor(Sensor sensor) {
        sensors.add(sensor);
    }

    public void draw(Graphics2D g) {
        for (Sensor s : sensors) {
            s.draw(g);
        }
    }

    public void move(MoveDirection direction) {
        int v = 10;
        position.setLocation(position.getX() + direction.movementX(v), position.getY() + direction.movementY(v));

        filter.moveParticles(direction, v);

        List<Double> sResult = new LinkedList<>();
        for (Sensor s : sensors) {
            s.updatePosition(position);
            sResult.add(s.sens());
        }
        filter.run(sResult);
    }
}
