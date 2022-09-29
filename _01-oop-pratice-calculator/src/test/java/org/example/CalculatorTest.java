package org.example;

import org.assertj.core.util.Streams;
import org.example.calculate.PositiveNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * - 간단한 사칙연산을 할 수 있다.
 * - 양수로만 계산할 수 있다.
 * - 나눗셈에서 0을 나누는 경우 IllegalArgument 예외를 발생시킨다.
 * - MVC패턴 (Model-View-Controller) 기반으로 구현한다.
 */
public class CalculatorTest {

    // 1 + 2 ---> Calculator
    //   3   <---
    @DisplayName("덧셈 연산을 수행한다.")
    @Test
    void additionTest() {
        int result = Calculator.calculate(new PositiveNumber(1), "+", new PositiveNumber(2));

        assertThat(result).isEqualTo(3);
    }

    @DisplayName("뺄셈 연산을 수행한다.")
    @Test
    void substractionTest() {
        int result = Calculator.calculate(new PositiveNumber(1), "-", new PositiveNumber(2));

        assertThat(result).isEqualTo(-1);
    }

    @DisplayName("사측연산을 수행한다.")
    @ParameterizedTest
    @MethodSource("formulaAndResult")
    void calculTest(int num1, String operator, int num2, int result) {
        int calculateResult = Calculator.calculate(new PositiveNumber(num1), operator, new PositiveNumber(num2));

        assertThat(calculateResult).isEqualTo(result);
    }

    private static Stream<Arguments> formulaAndResult() {
        return Stream.of(
                arguments(1, "+", 2, 3),
                arguments(1, "-", 2, -1),
                arguments(4, "*", 2, 8),
                arguments(4, "/", 2, 2)
        );
    }



}
