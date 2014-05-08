package my.study.misio.zad4.filters;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import my.study.misio.zad4.agents.Sensor;
import my.study.misio.zad4.env.Environment;

public class ParticipleFilter extends Filter {
	private List<Particle> particles = new LinkedList<>();
	private	int pNum;
	
	public ParticipleFilter(Environment env) {
		super(env);
		pNum = 200;
		Random gen = new Random();
		for(int i=0; i<pNum; i++){
			particles.add(new Particle(new Point(gen.nextInt(env.getWidth()), gen.nextInt(env.getHeight()))));
		}
	}
	
	public void test(){
		for(int i=0; i<pNum; i++){
			List<Double> sensorsResult = new LinkedList<>();
			//update sensors position to particle location
			for (Sensor s : sensors) {
				s.updatePosition(particles.get(i).getPosition(), 0);
				sensorsResult.add(s.sens());
			}
			System.out.println("Cz¹steczka: " + particles.get(i).getPosition().toString());
		}
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLACK);
		
		//drawing participles
		for (Particle p : particles) {
			Point2D pos = p.getPosition();
			//g.drawLine((int)pos.getX(), (int)pos.getY(), (int)pos.getX(), (int)pos.getY());
			g.drawOval((int)pos.getX(), (int)pos.getY(), 3, 3);
		}
	}

}
