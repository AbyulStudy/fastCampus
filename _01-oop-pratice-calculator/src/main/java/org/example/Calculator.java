package org.example;

import org.example.calculate.*;

import java.util.List;

public class Calculator {
    private static final List<NewArithmeticOperator> arithmeticOperators = List.of(new AdditionOperator(), new SubstractionOperator(), new MultiplicationOperator(), new DivisionOperator());

    public static int calculate(PositiveNumber num1, String operator, PositiveNumber num2) {
        return arithmeticOperators.stream()
                .filter(arithmeticOperators -> arithmeticOperators.supports(operator))
                .map(arithmeticOperators -> arithmeticOperators.calculate(num1, num2))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 사칙 연산이 아닙니다."));
    }
// enum 사용
//    public static int calculate(int num1, String operator, int num2) {
//        return ArithmeticOperator.calculate(num1, operator, num2);
//    }
}
