package org.example.calculate;

public class DivisionOperator implements NewArithmeticOperator {

    @Override
    public boolean supports(String operator) {
        return "/".equals(operator);
    }

    @Override
    public int calculate(PositiveNumber num1, PositiveNumber num2) {
        return num1.toInt() / num2.toInt();
    }
}
