package my.study.misio.zad4.sensors;

import my.study.misio.zad4.env.Environment;
import my.study.misio.zad4.gui.IDrawable;

import java.awt.geom.Point2D;

public abstract class Sensor implements IDrawable {

    final Environment env;
    protected Point2D location;

    Sensor(Environment e, Point2D a) {
        this.env = e;
        this.location = a;
    }

    public abstract double sens();

    public abstract void updatePosition(Point2D newPos);
}
