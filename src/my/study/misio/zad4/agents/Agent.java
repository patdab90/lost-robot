package my.study.misio.zad4.agents;

import java.awt.geom.Point2D;

import my.study.misio.zad4.gui.IDrawable;

public abstract class Agent implements IDrawable{
	protected Point2D position;
	
	public Point2D getPosition(){
		return position;
	}
}
