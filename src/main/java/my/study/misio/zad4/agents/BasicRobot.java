package my.study.misio.zad4.agents;

import my.study.misio.zad4.filters.Filter;

import java.awt.*;
import java.awt.geom.Point2D;

public class BasicRobot extends Agent {

    public BasicRobot(Filter filter, int posX, int posY) {
        super(filter);
        this.position = new Point2D.Double(posX, posY);
    }


    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        g.setColor(Color.ORANGE);
        g.fillOval((int) position.getX() - 8, (int) position.getY() - 8, 16, 16);
    }

}
