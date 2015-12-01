package my.study.misio.zad4.values;

import my.study.misio.zad4.RandomSingleton;

/**
 * Created by patryk on 04.10.15.
 */

public class ParticipleError implements my.study.misio.zad4.values.Error {

    @Override
    public boolean error() {
        return true;
    }

    @Override
    public double value(double originalValue) {
        return originalValue + RandomSingleton.getInstance().nextInt(5 * 2) - 5;
    }
}
