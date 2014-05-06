package my.study.misio.zad4.env;

import my.study.misio.zad4.gui.IBackground;

public abstract class Environment implements IBackground {
	protected int width;
	protected int height;

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}
}
