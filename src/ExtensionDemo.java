/** A new client demonstrates extension without changing the original Main. */
public final class ExtensionDemo {
    public static void main(String[] args) {
        Calculator calculator = new Calculator(new AddStrategy());
        System.out.println("Addition: 12.0 + 4.0 = " + calculator.calculate(12.0, 4.0));

        calculator.setStrategy(new DivideStrategy());
        System.out.println("Division: 12.0 / 4.0 = " + calculator.calculate(12.0, 4.0));

        try {
            calculator.calculate(12.0, 0.0);
        } catch (ArithmeticException exception) {
            System.out.println("Expected error: " + exception.getMessage());
        }
    }
}
