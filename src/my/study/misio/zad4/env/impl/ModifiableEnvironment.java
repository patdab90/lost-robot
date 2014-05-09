package my.study.misio.zad4.env.impl;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import my.study.misio.zad4.env.Environment;
import my.study.misio.zad4.gui.EnvironmentCanvas;

public class ModifiableEnvironment extends Environment implements MouseListener {

	private EnvironmentCanvas canvas;

	public ModifiableEnvironment() {
		width = 900;
		height = 900;
		
		this.creaeGraduation();
	}

	private Point2D clickStart = null;

	public void setCanvas(EnvironmentCanvas canvas){
		this.canvas = canvas;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		clickStart = new Point2D.Double(e.getX(), e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (clickStart != null) {
			addObstacle(new Line2D.Double(clickStart, new Point2D.Double(
					e.getX(), e.getY())));
			canvas.repaint();
		}
	}

}
