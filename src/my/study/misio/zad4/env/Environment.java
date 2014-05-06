package my.study.misio.zad4.env;

import java.awt.Shape;
import java.util.LinkedList;
import java.util.List;

import my.study.misio.zad4.gui.IBackground;

public abstract class Environment implements IBackground {
	protected int width;
	protected int height;
	protected List<Shape> shapes = new LinkedList<>();

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	public List<Shape> getObstacles() {
		return shapes;
	}
}
