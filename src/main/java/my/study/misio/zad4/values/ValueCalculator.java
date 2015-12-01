package my.study.misio.zad4.values;

/**
 * Created by patryk on 04.10.15.
 */
public class ValueCalculator {
    private final my.study.misio.zad4.values.Error error;

    public ValueCalculator(Error error) {
        this.error = error;
    }

    public double value(double originalValue){
        if(error.error()){
            return error.value(originalValue);
        }
        return originalValue;
    }
}
