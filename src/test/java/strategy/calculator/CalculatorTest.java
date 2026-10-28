package strategy.calculator;

/** Dependency-free behavioral checks; throws AssertionError on any failure. */
public final class CalculatorTest {
    private static int checks;

    public static void main(String[] args) {
        Calculator calculator = new Calculator(new AddStrategy());
        expectClose(16.0, calculator.calculate(12.0, 4.0), "addition");
        expectClose(-3.0, calculator.calculate(-5.0, 2.0), "negative operands");
        expectClose(0.3, calculator.calculate(0.1, 0.2), "decimal operands");

        calculator.setStrategy(new SubtractStrategy());
        expectClose(8.0, calculator.calculate(12.0, 4.0), "switch to subtraction");
        expectClose(-8.0, calculator.calculate(4.0, 12.0), "operand order");

        calculator.setStrategy(new MultiplyStrategy());
        expectClose(48.0, calculator.calculate(12.0, 4.0), "switch to multiplication");
        expectClose(0.0, calculator.calculate(-7.0, 0.0), "multiply by zero");

        calculator.setStrategy(new AddStrategy());
        expectClose(16.0, calculator.calculate(12.0, 4.0), "switch back to addition");

        calculator.setStrategy(new DivideStrategy());
        expectClose(3.0, calculator.calculate(12.0, 4.0), "division extension");
        expectClose(2.5, calculator.calculate(5.0, 2.0), "fractional quotient");
        expectClose(-3.0, calculator.calculate(-12.0, 4.0), "negative quotient");
        expectThrows(ArithmeticException.class,
                () -> calculator.calculate(12.0, 0.0), "positive zero divisor");
        expectThrows(ArithmeticException.class,
                () -> calculator.calculate(12.0, -0.0), "negative zero divisor");

        expectThrows(NullPointerException.class,
                () -> new Calculator(null), "null constructor argument");
        expectThrows(NullPointerException.class,
                () -> calculator.setStrategy(null), "null strategy replacement");
        expectClose(3.0, calculator.calculate(12.0, 4.0),
                "failed replacement preserves previous strategy");

        // An algorithm unknown to Calculator proves delegation to the interface.
        calculator.setStrategy((left, right) -> left * left + right * right);
        expectClose(25.0, calculator.calculate(3.0, 4.0), "custom strategy");

        System.out.println("All " + checks + " checks passed.");
    }

    private static void expectClose(double expected, double actual, String name) {
        if (!Double.isFinite(actual) || Math.abs(expected - actual) > 1e-9) {
            throw new AssertionError(name + ": expected " + expected + ", got " + actual);
        }
        checks++;
    }

    private static void expectThrows(Class<? extends Throwable> expected,
                                     Runnable action, String name) {
        try {
            action.run();
        } catch (Throwable actual) {
            if (!expected.isInstance(actual)) {
                throw new AssertionError(name + ": unexpected exception", actual);
            }
            checks++;
            return;
        }
        throw new AssertionError(name + ": expected " + expected.getSimpleName());
    }
}
