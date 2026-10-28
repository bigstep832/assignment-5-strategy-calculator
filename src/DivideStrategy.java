/** An extension added without modifying Calculator or its existing strategies. */
public final class DivideStrategy implements OperationStrategy {
    @Override
    public double execute(double left, double right) {
        if (right == 0.0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return left / right;
    }
}
