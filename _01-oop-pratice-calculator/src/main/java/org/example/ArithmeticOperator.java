package org.example;

import java.util.Arrays;

public enum ArithmeticOperator {
    ADDITION("+") {
        @Override
        public int arithmeticCalculate(int num1, int num2) {
            return num1 + num2;
        }
    }, SUBSTRACTION("-") {
        @Override
        public int arithmeticCalculate(int num1, int num2) {
            return num1 - num2;
        }
    }, MULTIPLICATION("*") {
        @Override
        public int arithmeticCalculate(int num1, int num2) {
            return num1 * num2;
        }
    }, DIVISION("/") {
        @Override
        public int arithmeticCalculate(int num1, int num2) {
            return num1 / num2;
        }
    };

    private final String operator;

    ArithmeticOperator(String operator) {
        this.operator = operator;
    }

    public abstract int arithmeticCalculate(final int num1, final int num2);

    public static int calculate(int num1, String operator, int num2) {
        ArithmeticOperator arithmeticOperator = Arrays.stream(values())
                .filter(v -> v.operator.equals((operator)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 사칙연산이 아닙니다."));

        return arithmeticOperator.arithmeticCalculate(num1,num2);
    }
}
