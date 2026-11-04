package hotstone.standard.Strategies.PrinterStrategy;

import hotstone.framework.Strategies.PrinterStrategy;

public class CommandLinePrinterStrategy implements PrinterStrategy {
    @Override
    public void print(String s) {
        System.out.print(s);
    }

    @Override
    public void printf(String format, Object... args) {
        System.out.printf(format, args);
    }
}
