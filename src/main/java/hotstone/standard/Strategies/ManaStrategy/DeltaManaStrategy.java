package hotstone.standard.Strategies.ManaStrategy;

import hotstone.framework.Strategies.ManaStrategy;

public class DeltaManaStrategy implements ManaStrategy {

    @Override
    public int calculate(int roundCount) {
        return 5;
    }

}
