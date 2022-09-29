package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;


/**
 * - 비밀번호는 최소 8자 이상 12자 이하
 * - 8자 미만 똔느 12자 초과인 경우 `illegalargumentexception` 예외를 발생
 * - 경계조건에 대해 테스트 코드를 작성
 */
public class PasswordValidatorTest {
    @DisplayName(("비밀번호가 최소 8자 이상, 12자 이하이면 예외 발생 하지 않음"))
    @Test
    void validatePasswordTest() {
        assertThatCode(() -> PasswordValidator.validate("devByul"))
                .doesNotThrowAnyException();
    }

    // 경계조건에 대해 테스트 코드작성
    @DisplayName("비밀번호가 최소 8자 미만, 12자 초과하는 경우 IllegalArgumentException 예외가 발생")
    @ParameterizedTest
    @ValueSource(strings = {"1234567","1234567890123"})
    void validatePasswordTest2(String password){

        assertThatCode(() -> PasswordValidator.validate(password))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("비밀번호는 8자 이상 12자 이하여야 한다.");
    }

}
