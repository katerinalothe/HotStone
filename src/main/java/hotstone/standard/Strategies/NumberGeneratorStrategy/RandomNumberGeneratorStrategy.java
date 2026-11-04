package hotstone.standard.Strategies.NumberGeneratorStrategy;
import hotstone.framework.Strategies.NumberGeneratorStrategy;

import java.util.Random;

public class RandomNumberGeneratorStrategy implements NumberGeneratorStrategy {
    @Override
    public int generate(int min, int max) {
        Random random = new Random();
        return min + random.nextInt(max - min + 1);
    }
}
