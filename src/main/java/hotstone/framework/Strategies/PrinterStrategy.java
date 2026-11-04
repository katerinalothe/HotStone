package hotstone.framework.Strategies;

public interface PrinterStrategy {

    /**
     * Prints the given string to the printer's output.
     *
     * @param s the string to be printed.
     */
    void print(String s);

    /**
     * Prints the arguments in the given format.
     *
     * @param format the desired format.
     * @param args the arguments to be formatted.
     */
    void printf(String format, Object... args);
}
