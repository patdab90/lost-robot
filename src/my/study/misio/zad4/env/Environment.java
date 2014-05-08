package my.study.misio.zad4.env;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.security.InvalidParameterException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import my.study.misio.zad4.gui.IBackground;

public abstract class Environment implements IBackground {
	protected int width;
	protected int height;
	protected int d = 20;

	protected List<Shape> shapes = new LinkedList<>();

	private int cellWidth;
	private int cellHeight;

	private class Entry {
		final public List<Shape> cell = new LinkedList<>();
	}

	protected Entry[][] graduation;

	public Environment() {
		this(20);
	}

	public Environment(int d) {
		this.d = d;
	}

	protected void creaeGraduation() {
		cellWidth = width / d;
		cellHeight = height / d;
		graduation = new Entry[cellWidth][cellHeight];
		for (int i = 0; i < cellWidth; i++) {
			for (int j = 0; j < cellHeight; j++) {
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

	public Iterator<List<Shape>> iterator(Line2D line) {
		return new EnvGraduationIterator(line);
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
				if (shape
						.intersects(new Rectangle2D.Double(i * d, j * d, d, d)))
					graduation[i][j].cell.add(shape);
			}
		}

	}

	private class EnvGraduationIterator implements Iterator<List<Shape>> {
		private final int deltax;
		private final int deltay;
		private List<Shape> current;
		private int currentX;
		private int currentY;

		public EnvGraduationIterator(Line2D line) {
			if (line.getX1() == line.getX2()) { // pion
				if (line.getY1() > line.getY2()) {
					deltax = 0;
					deltay = -1;
				} else {
					deltax = 0;
					deltay = 1;
				}
			} else if (line.getY1() == line.getY2()) { // poziom
				if (line.getX1() > line.getX2()) {
					deltax = -1;
					deltay = 0;
				} else {
					deltax = 1;
					deltay = 0;
				}
			} else {
				throw new InvalidParameterException();
			}
			int i = (int) (line.getX1() / d);
			int j = (int) (line.getY1() / d);
			getNext(i, j);
		}

		private void getNext(int x, int y) {
			int i = x;
			int j = y;
			current = null;
			while ((i < cellWidth && i >= 0) && (j < cellHeight && j >= 0)) {
				if (!graduation[i][j].cell.isEmpty()) {
					current = graduation[i][j].cell;
					currentX = i;
					currentY = j;
					break;
				}
				i += deltax;
				j += deltay;
			}
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public List<Shape> next() {
			List<Shape> result = current;
			getNext(currentX + deltax, currentY + deltay);
			return result;
		}

		@Override
		public void remove() {
			// unsupported
		}

	}

}
