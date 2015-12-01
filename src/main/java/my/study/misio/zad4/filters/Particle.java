package my.study.misio.zad4.filters;

import com.sun.istack.internal.NotNull;

import java.awt.geom.Point2D;

public class Particle implements Comparable<Particle> {
    private Point2D position;
    private double weight;

    public Particle(Point2D p) {
        setPosition(p);
        setWeight(0);
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(@SuppressWarnings("NullableProblems") @NotNull Particle o) {
        if (this.weight > o.weight)
            return -1;
        else if (this.weight < o.weight)
            return 1;
        return 0;
    }

    @Override
    public String toString() {
        return "Particle{" +
                "position=" + position +
                ", weight=" + weight +
                '}';
    }
}
