package my.study.misio.zad4;

import java.util.Random;

public final class RandomSingleton {
    private static final RandomSingleton instance = new RandomSingleton();
    private final Random random;

    private RandomSingleton() {
        random = new Random();
    }

    public static Random getInstance() {
        return instance.random;
    }
}
