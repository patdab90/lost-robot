package my.study.misio.zad4.filters;

import my.study.misio.zad4.RandomSingleton;
import my.study.misio.zad4.agents.MoveDirection;
import my.study.misio.zad4.env.Environment;
import my.study.misio.zad4.values.ValueCalculator;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;

public class ParticipleFilter extends Filter {
    private final ValueCalculator valueCalculator;
    private List<Particle> particles = new LinkedList<>();
    private final int pNum;
    protected ParticipleFilter(Environment environment, ValueCalculator valueCalculator, List<Particle> particles) {
        super(environment);
        this.valueCalculator = valueCalculator;
        this.pNum = particles.size();
        this.particles = particles;
    }

    public ParticipleFilter(Environment environment, int pNum, ValueCalculator valueCalculator) {
        this(environment, valueCalculator, createRandomParticiples(pNum, environment.getWidth(), environment.getHeight()));
    }

    static private List<Particle> createRandomParticiples(int pNum, double maxX, double maxY) {
        List<Particle> particles = new ArrayList<>();
        for (int i = 0; i < pNum; i++) {
            particles.add(new Particle(new Point(RandomSingleton.getInstance().nextInt((int) maxX), RandomSingleton.getInstance().nextInt((int) maxY))));
        }
        return particles;
    }

    protected List<Particle> getParticles() {
        return particles;
    }

    public void run(List<Double> robotSensorsResult) {
        double sum = calculateWeights(robotSensorsResult);
        //weights normalization
        normalizeWeights(sum);

        resample();
    }

    private void resample() {
        Collections.sort(particles);
        List<Particle> newParticles = new LinkedList<>();
        int generatedParticles = 0;
        int currParticleNum = 0;
        //resample
        while (generatedParticles < pNum && currParticleNum < particles.size()) {
            Particle p = particles.get(currParticleNum);
            newParticles.add(p);
            generatedParticles++;
            int newParticlesNum = (int) Math.ceil(p.getWeight() * pNum);
            if (newParticlesNum + generatedParticles >= pNum) {
                newParticlesNum = newParticlesNum + generatedParticles - pNum - 2;
            }

            for (int i = 0; i < newParticlesNum; i++) {
                newParticles.add(generateRandomParticle(p.getPosition()));
                generatedParticles++;
            }
            currParticleNum++;
        }
        particles = newParticles;
    }

    private void normalizeWeights(double sum) {
        for (Particle p : particles) {
            p.setWeight(p.getWeight() / sum);
        }
    }

    private double calculateWeights(List<Double> robotSensorsResult) {
        double sum = 0;
        //calculate weights for each particle
        for (Particle p : particles) {
            sum += calculateWeight(robotSensorsResult, p);
        }
        return sum;
    }

    private double calculateWeight(List<Double> robotSensorsResult, Particle p) {
        double totalDiff = 0.0;
        for (int j = 0; j < sensors.size(); j++) {
            sensors.get(j).updatePosition(p.getPosition());
            totalDiff += Math.abs(sensors.get(j).sens() - robotSensorsResult.get(j));
        }
        double tmpWeight = 1.0;
        if (Double.compare(0.0, totalDiff) != 0) {
            tmpWeight = 1.0 / totalDiff;
        }
        p.setWeight(tmpWeight);
        return tmpWeight;
    }

    private Particle generateRandomParticle(Point2D p) {
        double newX = newAxisValue(p.getX(), env.getWidth(), env.getHeight());
        double newY = newAxisValue(p.getY(), env.getHeight(), env.getWidth());
        return new Particle(new Point2D.Double(newX, newY));
    }

    private double newAxisValue(double pointAxisValue, double firstSize, double secondSize) {
        double axisValue = valueCalculator.value(pointAxisValue);
        if (axisValue < 0) {
            axisValue = 0;
        } else if (axisValue >= firstSize) {
            axisValue = secondSize - 1;
        }
        return axisValue;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);

        //drawing participles
        for (Particle p : particles) {
            Point2D pos = p.getPosition();
            g.drawOval((int) pos.getX(), (int) pos.getY(), 3, 3);
        }
    }

    @Override
    public void moveParticles(MoveDirection action, double velocity) {
        final double movementX = action.movementX(velocity);// + RandomSingleton.getInstance().nextInt((int) velocity) - velocity / 2;
        final double movementY = action.movementY(velocity);// + RandomSingleton.getInstance().nextInt((int) velocity) - velocity / 2;
        Iterator<Particle> it = particles.iterator();
        while (it.hasNext()) {
            moveParticiple(movementX, movementY, it);
        }
    }

    private void moveParticiple(double movementX, double movementY, Iterator<Particle> it) {
        double newX;
        double newY;
        Particle p = it.next();
        newX = (int) (p.getPosition().getX() + movementX);
        newY = (int) (p.getPosition().getY() + movementY);
        if (env.pointOutOfEnvironment(newX, newY)) {
            it.remove();
        } else {
            p.setPosition(new Point2D.Double(newX, newY));
        }
    }


}
