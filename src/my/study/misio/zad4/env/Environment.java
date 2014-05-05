package my.study.misio.zad4.env;

import my.study.misio.zad4.gui.IDrawable;

public abstract class Environment implements IDrawable {
	protected int width;
	protected int height;

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
