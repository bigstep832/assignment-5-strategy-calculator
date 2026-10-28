package strategy.calculator;

public final class AddStrategy implements OperationStrategy {
    @Override
    public double execute(double left, double right) {
        return left + right;
    }
}
