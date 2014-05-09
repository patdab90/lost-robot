package my.study.misio.zad4.agents;

import java.awt.geom.Point2D;

public enum Action {
	UP(new Point2D.Double(0, -1)),DOWN(new Point2D.Double(0, 1)), LEFT(new Point2D.Double(-1, 0)), RIGHT(new Point2D.Double(1, 0));
	
	private Point2D coords;
	
	Action(Point2D c){
		coords = c;
	}
	
	public Point2D getCoords(){
		return coords;
	}
}
