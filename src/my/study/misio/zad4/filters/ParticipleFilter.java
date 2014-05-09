package my.study.misio.zad4.filters;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import my.study.misio.zad4.RandomSingleton;
import my.study.misio.zad4.env.Environment;

public class ParticipleFilter extends Filter {
	private List<Particle> particles = new LinkedList<>();
	private	int pNum;
	private int resampleRandomVar;
	
	public ParticipleFilter(Environment env) {
		super(env);
		pNum = 1000;
		resampleRandomVar = 5;
		for(int i=0; i<pNum; i++){
			particles.add(new Particle(new Point(RandomSingleton.getInstance().nextInt(env.getWidth()), RandomSingleton.getInstance().nextInt(env.getHeight()))));
		}
	}
	
	public void run(List<Double> robotSensorsResult){
		double sum = 0;
		//calculate weights for each particle
		for(Particle p : particles){
			double totalDiff = 0;
			for (int j=0; j<sensors.size(); j++) {
				sensors.get(j).updatePosition(p.getPosition(), 0);
				totalDiff += Math.abs(sensors.get(j).sens() - robotSensorsResult.get(j));
			}
			double tmpWeight = 1/totalDiff;
			p.setWeight(tmpWeight);
			sum += tmpWeight;
		}
		
		//weights normalization
		for(Particle p : particles){
			p.setWeight(p.getWeight()/sum);
		}
		
		Collections.sort(particles);
		
		List<Particle> newParticles = new LinkedList<>();
		int generatedParticles = 0;
		int currParticleNum = 0;
		//resample
		while(generatedParticles < pNum && currParticleNum < particles.size()){
			Particle p = particles.get(currParticleNum);
			newParticles.add(p);
			generatedParticles++;
			int newParticlesNum = (int) Math.ceil(p.getWeight()*pNum);
			if(newParticlesNum+generatedParticles >= pNum){
				newParticlesNum = newParticlesNum+generatedParticles-pNum-2;
			}
			
			for(int i=0; i<newParticlesNum; i++){
				newParticles.add(generateRandomParticle(p.getPosition(), resampleRandomVar));
				generatedParticles++;
			}
			currParticleNum++;
		}
		particles = newParticles;
		/*for(int i=0; i<pNum; i++){
			System.out.println("Cz¹steczka: " + particles.get(i).getPosition().toString() + "Waga: " + particles.get(i).getWeight());
		}*/
	}
	
	private Particle generateRandomParticle(Point2D p, int randomAttr){
		int newX = (int) p.getX()+RandomSingleton.getInstance().nextInt(randomAttr*2)-randomAttr;
		if(newX < 0)
			newX = 0;
		else if(newX >= env.getWidth())
			newX = env.getWidth()-1;
		
		int newY = (int) p.getY()+RandomSingleton.getInstance().nextInt(randomAttr*2)-randomAttr;
		if(newY < 0)
			newY = 0;
		else if(newY >= env.getHeight())
			newY = env.getHeight()-1;
		
		Particle result = new Particle(new Point(newX, newY));
		return result;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		
		//drawing participles
		for (Particle p : particles) {
			Point2D pos = p.getPosition();
			//g.drawLine((int)pos.getX(), (int)pos.getY(), (int)pos.getX(), (int)pos.getY());
			g.drawOval((int)pos.getX(), (int)pos.getY(), 3, 3);
			//g.drawString(Double.toString(p.getWeight()), (int)pos.getX()+4, (int)pos.getY()+4);
		}
	}

	@Override
	public void moveParticles(double x, double y) {
		int newX;
		int newY;
		boolean deleted;
		Iterator it = particles.iterator();
		while (it.hasNext()) {
			Particle p = (Particle) it.next();
			newX = (int) (p.getPosition().getX() + x);
			newY = (int) (p.getPosition().getY() + y);
			deleted = false;
			//delete particle if it's out of env
			if(newX < 0){
				it.remove();
				deleted = true;
			}else if(newX >= env.getWidth()){
				it.remove();
				deleted = true;
			}
			
			if(deleted == false && newY < 0){
				it.remove();
				deleted = true;
			}else if(newY >= env.getHeight()){
				it.remove();
				deleted = true;
			}
			
			if(!deleted){
				p.setPosition(new Point(newX, newY));
			}
		}
	}

}
