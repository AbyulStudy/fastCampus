package org.example.calculate;

public interface NewArithmeticOperator {
    boolean supports(String operator);

    int calculate(PositiveNumber num1, PositiveNumber num2);
}
