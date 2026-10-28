package strategy.calculator;

/** Client: selects algorithms and switches them on the same context instance. */
public final class Main {
    public static void main(String[] args) {
        double left = 12.0;
        double right = 4.0;
        Calculator calculator = new Calculator(new AddStrategy());

        System.out.println("One Calculator instance; strategies change at runtime.");
        System.out.println("12.0 + 4.0 = " + calculator.calculate(left, right));

        calculator.setStrategy(new SubtractStrategy());
        System.out.println("12.0 - 4.0 = " + calculator.calculate(left, right));

        calculator.setStrategy(new MultiplyStrategy());
        System.out.println("12.0 * 4.0 = " + calculator.calculate(left, right));

        calculator.setStrategy(new AddStrategy());
        System.out.println("Back to addition: 12.0 + 4.0 = "
                + calculator.calculate(left, right));
    }
}
