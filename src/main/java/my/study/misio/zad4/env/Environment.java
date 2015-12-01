package my.study.misio.zad4.env;

import my.study.misio.zad4.agents.MoveDirection;
import my.study.misio.zad4.gui.IBackground;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class Environment implements IBackground {
    protected int width;
    protected int height;
    Entry[][] graduation;
    private final int d;
    private final List<Shape> shapes = new LinkedList<>();
    private int cellWidth;
    private int cellHeight;

    protected Environment() {
        this(20);
    }

    Environment(int d) {
        this.d = d;
    }

    final protected void createGraduation() {
        cellWidth = obstacleSize(width);
        cellHeight = obstacleSize(height);
        graduation = new Entry[cellWidth][cellHeight];
        for (int i = 0; i < cellWidth; i++) {
            for (int j = 0; j < cellHeight; j++) {
                graduation[i][j] = new Entry();
            }
        }
    }

    private int obstacleSize(double axisSize) {
        return (int) Math.ceil(axisSize / (double) d);
    }

    public boolean pointOutOfEnvironment(double newX, double newY) {
        return newX < 0 || newX >= getWidth() || newY < 0 || newY >= getHeight();
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

    protected void addObstacle(Shape shape) {
        shapes.add(shape);
        Rectangle2D r = shape.getBounds2D();
        int x1 = obstacleNumber(r.getX());
        int y1 = obstacleNumber(r.getY());

        int x2 = obstacleNumber((r.getX() + r.getWidth()));
        int y2 = obstacleNumber((r.getY() + r.getHeight()));

        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if (shape
                        .intersects(new Rectangle2D.Double(i * d, j * d, d, d)))
                    graduation[i][j].cell.add(shape);
            }
        }

    }

    private int obstacleNumber(double axisValue) {
        return (int) Math.floor(axisValue / d);
    }

    public Iterator<Shape> shapeIterator(Line2D.Double line) {
        return new ShapeIterator(line);
    }

    public class Entry {
        final public List<Shape> cell = new LinkedList<>();
    }

    class EnvGraduationIterator implements Iterator<List<Shape>> {
        final MoveDirection direction;
        private List<Shape> cell;
        private int currentX;

        private int currentY;

        public EnvGraduationIterator(Line2D line) {
            direction = MoveDirection.direction(line.getX1(), line.getY1(), line.getX2(), line.getY2());
            int i = (int) (line.getX1() / d);
            int j = (int) (line.getY1() / d);
            getNext(i, j);
        }

        private void getNext(int x, int y) {
            int i = x;
            int j = y;
            cell = null;
            while ((i < cellWidth && i >= 0) && (j < cellHeight && j >= 0)) {
                if (!graduation[i][j].cell.isEmpty()) {
                    cell = graduation[i][j].cell;
                    currentX = i;
                    currentY = j;
                    break;
                }
                i = (int) direction.increaseX(i);
                j = (int) direction.increaseY(j);
            }
        }

        @Override
        public boolean hasNext() {
            return cell != null;
        }

        @Override
        public List<Shape> next() {
            List<Shape> result = cell;
            getNext((int) direction.increaseX(currentX), (int) direction.increaseY(currentY));
            return result;
        }

        @Override
        public void remove() {
            // unsupported
        }

    }

    private class ShapeIterator implements Iterator<Shape> {
        private final Iterator<List<Shape>> it;
        private Shape current;
        private int index = 0;
        private List<Shape> currentCell;

        public ShapeIterator(Line2D line) {
            it = new EnvGraduationIterator(line);
            if (it.hasNext()) {
                currentCell = it.next();
                current = getNext();
            }
        }

        private Shape getNext() {
            if (index >= currentCell.size()) {
                if (it.hasNext()) {
                    currentCell = it.next();
                } else {
                    return null;
                }
                index = 0;
                if (currentCell.isEmpty()) {
                    return null;
                }
            }
            return currentCell.get(index);
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Shape next() {
            Shape shape = current;
            index++;
            current = getNext();
            return shape;
        }
    }
}
