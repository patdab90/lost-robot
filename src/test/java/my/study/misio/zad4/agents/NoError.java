package my.study.misio.zad4.agents;

/**
 * Created by patryk on 04.10.15.
 */
public class NoError implements my.study.misio.zad4.values.Error {
    @Override
    public boolean error() {
        return false;
    }

    @Override
    public double value(double originalValue) {
        return originalValue;
    }

}
