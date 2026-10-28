# Strategy Calculator

Assignment 5, option B. Group: SE-2538. Java 17+, no external libraries.

`Calculator` delegates to `OperationStrategy`. `Main` switches addition,
subtraction and multiplication on the same calculator at runtime. `ExtensionDemo`
adds division without changing the original classes.

## Files

```text
src/main/java/strategy/calculator/
  OperationStrategy.java   Strategy interface
  Calculator.java          Context
  AddStrategy.java         Concrete strategy
  SubtractStrategy.java    Concrete strategy
  MultiplyStrategy.java    Concrete strategy
  DivideStrategy.java      Extension
  Main.java                Runtime switching demonstration
  ExtensionDemo.java       Extension and error demonstration
src/test/java/strategy/calculator/CalculatorTest.java
docs/uml.png               Portable UML diagram
REPORT.md                  Six-section assignment report (English)
```

## Build and run (PowerShell)

From the project root, with JDK 17+ on `PATH`:

```powershell
New-Item -ItemType Directory -Force out | Out-Null
$sources = Get-ChildItem -Recurse src/main/java -Filter *.java | ForEach-Object FullName
javac --release 17 -encoding UTF-8 -d out $sources
java -cp out strategy.calculator.Main
java -cp out strategy.calculator.ExtensionDemo
```

## Run checks

```powershell
$tests = Get-ChildItem -Recurse src/test/java -Filter *.java | ForEach-Object FullName
javac --release 17 -encoding UTF-8 -cp out -d out $tests
java -cp out strategy.calculator.CalculatorTest
```

Expected: `All 17 checks passed.` These cover arithmetic, switching and switching
back, operand order, a new custom strategy, null strategy rejection, preserving
the previous strategy after a rejected replacement, and both signed zero divisors.

On macOS/Linux, compile and run from the project root:

```sh
mkdir -p out
find src/main/java src/test/java -name '*.java' > out/sources.txt
javac --release 17 -encoding UTF-8 -d out @out/sources.txt
java -cp out strategy.calculator.Main
java -cp out strategy.calculator.ExtensionDemo
java -cp out strategy.calculator.CalculatorTest
```

In IntelliJ IDEA, open this directory, select JDK 17+ as Project SDK, mark
`src/main/java` as Sources Root and `src/test/java` as Test Sources Root, and run
`Main.main`, `ExtensionDemo.main` or `CalculatorTest.main`.

## Expected demonstration

```text
One Calculator instance; strategies change at runtime.
12.0 + 4.0 = 16.0
12.0 - 4.0 = 8.0
12.0 * 4.0 = 48.0
Back to addition: 12.0 + 4.0 = 16.0
```

The extension prints `3.0` for `12 / 4` and a handled `Cannot divide by zero`
error for `12 / 0`.

## Add an operation

Implement `OperationStrategy.execute(double left, double right)` in a new class.
Pass its instance to the calculator constructor or `setStrategy`. The context
and the existing strategies require no changes.

For example, add `PowerStrategy implements OperationStrategy`, implement
`return Math.pow(left, right);`, then select it in a new client or update the
existing client's configuration. A new operation still needs client wiring;
Strategy does not automatically discover classes. A separate Git commit is an
actual example: it adds only `DivideStrategy` and `ExtensionDemo`.

## Numeric behavior and validation

This teaching example uses Java `double`, including normal rounding, NaN and
overflow behavior. It is not an exact-decimal calculator. Division deliberately
rejects both `0.0` and `-0.0` with `ArithmeticException`. The context rejects a
null strategy using `Objects.requireNonNull`.

Validation on this computer used JDK 21.0.10 with `javac --release 17
-Xlint:all -Werror`, followed by both demos and all 17 checks on Java 21.
The resulting classes target Java 17; a Java 17 runtime was not installed here.

## Report and publication

See [REPORT.md](REPORT.md) and [the diagram](docs/uml.png).
The report contains one UML class diagram. Its editable PlantUML source is
available in `docs/uml.puml`. Every relationship is labeled.

[GitHub repository](https://github.com/bigstep832/assignment-5-strategy-calculator)
