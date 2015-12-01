package my.study.misio.zad4.values;

import my.study.misio.zad4.RandomSingleton;

/**
 * Created by patryk on 04.10.15.
 */
public class ObstacleSensorError implements Error {

    public ObstacleSensorError() {
        this(0.01);
    }

    private ObstacleSensorError(double chance) {
        this.chance = chance;
    }

    private final double chance;

    @Override
    public boolean error() {
        return RandomSingleton.getInstance().nextDouble() < chance;
    }

    @Override
    public double value(double originalValue) {
        return originalValue > 0.0 ? 0.0 : 1.0;
    }
}
