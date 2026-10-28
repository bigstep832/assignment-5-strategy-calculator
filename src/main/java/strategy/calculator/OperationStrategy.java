package strategy.calculator;

/** An interchangeable binary arithmetic operation using Java double arithmetic. */
@FunctionalInterface
public interface OperationStrategy {
    double execute(double left, double right);
}
