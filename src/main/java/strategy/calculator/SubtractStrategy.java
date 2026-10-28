package strategy.calculator;

public final class SubtractStrategy implements OperationStrategy {
    @Override
    public double execute(double left, double right) {
        return left - right;
    }
}
