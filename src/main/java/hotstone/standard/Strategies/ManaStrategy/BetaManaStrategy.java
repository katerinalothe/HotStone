package hotstone.standard.Strategies.ManaStrategy;

import hotstone.framework.Strategies.ManaStrategy;

public class BetaManaStrategy implements ManaStrategy {
    @Override
    public int calculate(int roundCount) {
        return Math.min(roundCount, 7);
    }
}
