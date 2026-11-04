package hotstone.standard.utilities;

import hotstone.framework.Strategies.NumberGeneratorStrategy;

public class FixedGeneratorStrategy implements NumberGeneratorStrategy {

    private int value;

    public FixedGeneratorStrategy(int initialValue) {
        value = initialValue;
    }

    @Override
    public int generate(int min, int max) {
        return value;
    }

    public void set(int value) {
        this.value = value;
    }

}
