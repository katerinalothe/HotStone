package hotstone.framework.Strategies;

/** Strategy for generating numbers. */
public interface NumberGeneratorStrategy {

    /** Generate a number in some bounds, inclusive.
     *
     * @param min the smallest possible number.
     * @param max the greatest possible number.
     * @return a number in the range [min,max].
     */
    int generate(int min, int max);
}
