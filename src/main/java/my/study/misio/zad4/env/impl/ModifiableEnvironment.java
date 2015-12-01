package my.study.misio.zad4.env.impl;

import my.study.misio.zad4.env.Environment;
import my.study.misio.zad4.gui.EnvironmentCanvas;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class ModifiableEnvironment extends Environment implements MouseListener {

    private EnvironmentCanvas canvas;
    private Point2D clickStart = null;
    private boolean line = true;

    public ModifiableEnvironment() {
        width = 900;
        height = 900;

        this.createGraduation();
    }

    public void setCanvas(EnvironmentCanvas canvas) {
        this.canvas = canvas;
        this.canvas.addMouseListener(this);
    }

    public void mouseClicked(MouseEvent e) {
    }


    public void mouseEntered(MouseEvent e) {
    }


    public void mouseExited(MouseEvent e) {
    }


    public void mousePressed(MouseEvent e) {
        clickStart = new Point2D.Double(e.getX(), e.getY());
        line = !SwingUtilities.isRightMouseButton(e);
    }

    public void mouseReleased(MouseEvent e) {
        if (clickStart != null) {
            if (line) {
                addObstacle(new Line2D.Double(clickStart, new Point2D.Double(
                        e.getX(), e.getY())));
            } else {

                addObstacle(new Rectangle2D.Double(Math.min(clickStart.getX(),
                        e.getX()), Math.min(clickStart.getY(), e.getY()),
                        Math.max(clickStart.getX(), e.getX())
                                - Math.min(clickStart.getX(), e.getX()),
                        Math.max(clickStart.getY(),
                                e.getY()) - Math.min(clickStart.getY(),
                                e.getY())));
            }
            canvas.repaint();
        }
    }

}
