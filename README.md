# Java Mathematical Expression Evaluator

A custom mathematical expression evaluator written in Java, created to solve the **[Evaluate mathematical expression](https://www.codewars.com/kata/evaluate-mathematical-expression)** Codewars kata.

The project implements a mathematical expression parser and evaluator without relying on external expression-evaluation libraries.

## Features

- Basic arithmetic operations: `+`, `-`, `*`, `/`
- Parentheses and nested expressions
- Decimal numbers
- Negative numbers and unary operators
- Operator precedence
- Whitespace handling
- Interactive terminal calculator

## Usage

### Requirements

- Java JDK installed
- No external dependencies required

### Run the project

Compile the source files:

```bash
javac -d out src/*.java
```

Run the application:

```bash
java -cp out Main
```

### Interactive calculator

Once running, enter mathematical expressions directly into the terminal.

```text
> 2 + 2
4.000000

> 10 / 2
5.000000

> 2 / 2 + 3 * 4.75 - -6
21.250000

> (13 - 2) / -(-11)
1.000000

> exit
Goodbye!
```

Type `exit` to terminate the program.

### Using the evaluator in Java

You can also use the evaluator directly in your own Java code:

```java
MathEvaluator evaluator = new MathEvaluator();

double result = evaluator.calculate("2 + 3 * (4 - 1)");

System.out.println(result); // 11.0
```

## Codewars

This project was created as a personal implementation of the Codewars kata:

**Evaluate mathematical expression**

The goal is to parse and evaluate mathematical expressions while respecting operator precedence, parentheses, and unary operators.

## License

This project is licensed under MIT License.
