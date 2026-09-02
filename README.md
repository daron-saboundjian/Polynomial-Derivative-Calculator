# Polynomial Derivative Calculator

A simple Java Swing application that calculates the derivative of a polynomial function, with support for higher-order derivatives (2nd derivative, 3rd derivative, etc.).

## Features

- Calculates derivatives of polynomials using the power rule
- Supports repeated differentiation (nth derivative)
- Handles negative and decimal exponents
- Simple GUI built with Java Swing
- Input validation with error popups for invalid input

## How It Works

The program takes a polynomial equation (as a string) and a derivative order (how many times to differentiate) as input. It then:

1. **Parses** the equation into individual terms, correctly distinguishing negative signs that start a new term from negative exponents (e.g. `9x^-3` vs `-4x^3`)
2. **Differentiates** each term using the power rule (`d/dx(ax^n) = n*ax^(n-1)`), repeating recursively for the requested number of derivatives
3. **Formats** the result back into a clean polynomial string (removing unnecessary decimals, simplifying coefficients of 1 and -1, etc.)
4. **Displays** the final derivative in a popup dialog

## Usage

1. Compile and run `Main.java`
2. In the GUI window, enter your polynomial in the "Input Function" field
3. Enter the derivative order (must be 1 or greater) in the "Enter Derivative Order" field
4. Click **Submit** to see the result in a popup

### Input Rules

- Only input polynomials (decimal exponents are permitted)
- Do not add spaces
- Do not use parentheses
- Use `^` to express exponents
- Express fractions as decimal values
- Use `x` for the independent variable

### Example

**Input:** `8x^3+1.5x^2-x^1.5+3x`
**Derivative Order:** `1`

## Project Structure

| File | Description |
|------|-------------|
| `Main.java` | Entry point — launches the application |
| `Frame.java` | Builds the GUI (Swing) and handles user input/events |
| `Calc.java` | Core logic — parses and differentiates the polynomial |

## Requirements

- Java (JDK 8+ recommended) with Swing support

## Building & Running

\`\`\`bash
javac Main.java Frame.java Calc.java
java Main
\`\`\`