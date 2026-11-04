package hotstone.standard.Strategies.ManaStrategy;

import hotstone.framework.Strategies.ManaStrategy;

public class AlphaManaStrategy implements ManaStrategy {

    @Override
    public int calculate(int roundCount) {
        return 3;
    }
}
