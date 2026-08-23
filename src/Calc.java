import java.util.ArrayList;
public class Calc {

    private String equation;
    private ArrayList<String> components = new ArrayList<String>();
    private String result;
    private int count;

    // Initializes the calculator with the user's polynomial 
    // and the number of times the expression should be differentiated
    Calc(String equation, int count) { 
        this.equation = equation;
        this.count = count;
    }

    // Splits the polynomial into individual terms and stores them
    // in the components ArrayList. Negative exponents are preserved
    // by treating '-' after '^' as part of the exponent rather than
    // the start of a new term.
    private void seperate() {                                
        String term = "";
        for (int i = 0; i < equation.length(); i++) {
            String current = equation.substring(i, i + 1);
            if (current.equals("+")) { // '+' marks the end of the current term.
                if (!term.equals("")) {
                    components.add(term);
                }
                term = "";

            // If '-' follows '^', it belongs to a negative exponent
            // rather than indicating the start of a new term that happens to start with a '-' (ex. -4x^3).    
            } else if (current.equals("-")){ 
                if (i > 0 && equation.substring(i - 1, i).equals("^")) { 
                    term += current;
                } else {
                    if (!term.equals("")) {
                        components.add(term);
                    }
                    term = "-";
                }
            } else {
                term += current; 
            }
        }
        // Adds the final term to the components ArrayList
        if (!term.equals("")) {
            components.add(term);
        }
    }

    // Applies the power rule to each term in the polynomial.
    // Recursively calls itself until the requested derivative
    // count has been reached.
    private void derive(int count) {
        for(int i = 0; i < components.size(); i++) {
            String derivedTerm;
            String term = components.get(i);
            boolean hasX = term.contains("x");
            
            if (term.indexOf("^") != -1) {  // Handles terms written in power form (ax^n) using the power rule where n > 1.
                String coefficient = term.substring(0, term.indexOf("x"));
                double base;
                double exponent = Double.parseDouble(term.substring(term.indexOf("^") + 1));

                if (coefficient.equals("")) {  // No coefficient means an implied coefficient of 1.
                    base = 1;
                } else if (coefficient.equals("-")) { // A leading '-' implies a coefficient of -1.
                    base = -1;
                } else {
                    base = Double.parseDouble(coefficient);
                }

                // Derivative power rule: d/dx(ax^n) = n*ax^(n-1).
                double derivedBase = base * exponent; 
                double derivedExponent = exponent - 1;

                String baseStr = cleanCoeff(formatNumber(derivedBase));

                // x^0 simplifies to a constant, so only the coefficient remains.
                if (Math.abs(derivedExponent) < 0.0000001) {
                    derivedTerm = baseStr;
                } else if (Math.abs(derivedExponent - 1) < 0.0000001) {
                    derivedTerm = baseStr + "x";
                } else {
                    derivedTerm = baseStr + "x^" + formatNumber(derivedExponent);
                }
              
            // Handles first-degree terms that do not  contain '^' (ex. 3x).
            } else if (hasX) {
                String coefficient = term.substring(0, term.indexOf("x"));
                if (coefficient.equals("") || coefficient.equals("+")) {
                    derivedTerm = "1";
                } else if (coefficient.equals("-")) {
                    derivedTerm = "-1";
                } else {
                    derivedTerm = cleanCoeff(formatNumber(Double.parseDouble(coefficient)));
                }
            } else {
                derivedTerm = "0"; // The derivative of any constant is 0.
            }

            components.set(i, derivedTerm);
        }

        // Continue differentiating via recursion until the requested derivative
        // order has been reached.
        if (count > 1) {
            ArrayList<String> cloneComponents = new ArrayList<>(components); // Create a copy of the current derivative before the next recursive pass.
            components = cloneComponents;
            derive(count - 1);
        }
    }

    // Removes unnecessary decimal places from whole numbers for cleaner output (ex. 6.0 -> 6).
    private String formatNumber(double value) {
        // Floating-point comparison used to deal with values that
        // are effectively integers despite Java rounding errors.
        if (Math.abs(value - (int)value) < 0.0000001) {
            return String.valueOf((int) value);
        }
        return String.valueOf(value);
    }

    // Simplifies coefficients for cleaner mathematical notation.
    private String cleanCoeff(String coeff) {  
        if (coeff.equals("1")) { // Display x instead of 1x.
            return "";
        }
        if (coeff.equals("-1")) { // Display -x instead of -1x.
            return "-";
        }
        return coeff;
    }

    // Combines all derived terms in the components ArrayList into a single polynomial string.
    private void stringConversion() {
        for (int i = 0; i < components.size(); i++) {
            String term = components.get(i);
            // The first term does not need a leading '+' sign.
            if (i == 0) {
                result += term;
            } else {
                if (term.startsWith("-")) { // Negative terms already contain their sign.
                    result += term;
                } else { // Positive terms are prefixed with '+' when not first.
                    result += "+" + term;
                }
            }
        }
    }

    // Main driver method: parses the polynomial, performs the
    // requested derivatives, converts the result to a string,
    // and displays the final answer.
    public String calculate() {
        result = "";
        components.clear();
        seperate();
        derive(count);

        if (components.isEmpty()) {
            components.add("0");
        }

        stringConversion();
        return result;
    }
}
