package my.study.misio.zad4.env;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.List;

import my.study.misio.zad4.gui.IBackground;

public abstract class Environment implements IBackground {
	protected int width;
	protected int height;
	protected int d = 20;
	protected List<Shape> shapes = new LinkedList<>();

	protected class Entry {
		final public List<Shape> cell = new LinkedList<>();
	}

	protected Entry[][] graduation;

	public Environment() {

	}

	public Environment(int d) {
		this();
		this.d = d;
	}

	protected void creaeGraduation() {
		graduation = new Entry[(width / d)][(height / d)];
		for (int i = 0; i < (width / d); i++) {
			for (int j = 0; j < (height / d); j++) {
				graduation[i][j] = new Entry();
			}
		}
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	/**
	 * First x, y is begining of line
	 * 
	 * @param line
	 * @return
	 */
	public List<Shape> getNearestObstacleOnLine(Line2D line) {
		if (line.getX1() == line.getX2()) { // pion
			if (line.getY1() > line.getY2()) {
				int x = (int) (line.getX1() / d);
				for (int i = (int) (line.getY1() / d); i >= 0; i--) {
					if (!graduation[x][i].cell.isEmpty()) {
						return graduation[x][i].cell;
					}
				}
			} else {
				int x = (int) (line.getX1() / d);
				for (int i = (int) (line.getY1() / d); i < (int) (width / d); i++) {
					if (!graduation[x][i].cell.isEmpty()) {
						return graduation[x][i].cell;
					}
				}
			}
		} else if (line.getY1() == line.getY2()) { // poziom
			if (line.getX1() > line.getX2()) {
				int y = (int) (line.getY1() / d);
				for (int i = (int) (line.getX1() / d); i >= 0; i--) {
					if (!graduation[i][y].cell.isEmpty()) {
						return graduation[i][y].cell;
					}
				}
			} else {
				int y = (int) (line.getY1() / d);
				for (int i = (int) (line.getX1() / d); i < (int) (height / d); i++) {
					if (!graduation[i][y].cell.isEmpty()) {
						return graduation[i][y].cell;
					}
				}
			}

		}
		return null;
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.RED);
		for (Shape s : shapes) {
			g.draw(s);
			g.fill(s);
		}
	}

	protected List<Shape> getObstacles() {
		return shapes;
	}

	protected void addObstacle(Shape shape) {
		shapes.add(shape);
		Rectangle2D r = shape.getBounds2D();
		int x1 = (int) Math.floor(r.getX() / d);
		int y1 = (int) Math.floor(r.getY() / d);

		int x2 = (int) Math.floor((r.getX() + r.getWidth()) / d);
		int y2 = (int) Math.floor((r.getY() + r.getHeight()) / d);

		for (int i = x1; i <= x2; i++) {
			for (int j = y1; j <= y2; j++) {
				if (shape.intersects(new Rectangle2D.Double(i*d, j*d, d, d)))
					graduation[i][j].cell.add(shape);
			}
		}

	}
	
}
