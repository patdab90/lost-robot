package my.study.misio.zad4.filters;

import my.study.misio.zad4.env.Environment;
import my.study.misio.zad4.gui.IDrawable;

public abstract class Filter implements IDrawable {
	protected Environment env;

	public Filter(Environment env) {
		this.env = env;
	}
}
