# Strategy Calculator

Assignment 5, option B. Group: SE-2538. Java 17+, no external libraries.

`Calculator` delegates to `OperationStrategy`. `Main` switches addition,
subtraction and multiplication on the same calculator at runtime.
`ExtensionDemo` demonstrates division without modifying the existing classes.

## Project structure

```text
src/
  OperationStrategy.java
  Calculator.java
  AddStrategy.java
  SubtractStrategy.java
  MultiplyStrategy.java
  DivideStrategy.java
  Main.java
  ExtensionDemo.java
docs/uml.png
README.md
```

## Build and run

From the project root, with JDK 17+ on `PATH`:

```powershell
javac --release 17 -encoding UTF-8 -d out src/*.java
java -cp out Main
java -cp out ExtensionDemo
```

In IntelliJ IDEA, open the project directory, select JDK 17+ as Project SDK,
mark `src` as Sources Root, and run `Main` or `ExtensionDemo`.

For operands 12 and 4, `Main` prints 16, 8, 48 and 16 while changing the strategy.
`ExtensionDemo` prints 3 for division and handles division by zero with a clear error.

## Add a strategy

Create a class implementing `OperationStrategy.execute(double left, double right)`.
Pass its instance to the constructor or `setStrategy` from a client. For example,
a `PowerStrategy` could return `Math.pow(left, right)`. `Calculator` and the
existing strategies require no changes. New operations still need client wiring.

The division commit demonstrates this: it adds only `DivideStrategy` and
`ExtensionDemo`. The context has no `if` or `switch` on the operation type.

## UML diagram

See [the UML diagram](docs/uml.png).

[GitHub repository](https://github.com/bigstep832/assignment-5-strategy-calculator)
