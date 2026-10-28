import java.util.Objects;

/** Context: delegates calculations without knowing any concrete algorithm. */
public final class Calculator {
    private OperationStrategy strategy;

    public Calculator(OperationStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy, "Strategy must not be null");
    }

    public void setStrategy(OperationStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy, "Strategy must not be null");
    }

    public double calculate(double left, double right) {
        return strategy.execute(left, right);
    }
}
