package my.study.misio.zad4.env;

import java.awt.*;

/**
 * Created by patryk on 03.10.15.
 */
public class TestEnvironment extends Environment {

    public TestEnvironment(int d, int width, int height) {
        super(d);
        this.width = width;
        this.height = height;
        createGraduation();
    }

    public void addObstacle(Shape shape){
        super.addObstacle(shape);
    }
}
