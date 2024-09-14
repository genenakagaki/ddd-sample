package com.genenakagaki.checklist.user.domain;

import com.genenakagaki.checklist.domain.DomainValidationException;
import com.genenakagaki.checklist.domain.user.Username;
import com.genenakagaki.checklist.domain.user.UsernameLengthException;
import com.genenakagaki.checklist.domain.user.UsernamePatternException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UsernameTest {

    @Nested
    class LengthValidation {
        @Test
        void username_whereLengthIsLessThan8_shouldThrowException() {
            Assertions.assertThatExceptionOfType(DomainValidationException.class)
                    .isThrownBy(() -> {
                        Username.create("1234567");
                    });
        }

        @Test
        void username_whereLengthIsMoreThan32_shouldThrowException() {
            Assertions.assertThatExceptionOfType(DomainValidationException.class)
                    .isThrownBy(() -> {
                        Username.create("123456789012345678901234567890123");
                    });
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "12345678", // 8
                "123456789", // 9
                "1234567890123456789012345678901", // 31
                "12345678901234567890123456789012" // 32
        })
        void username_whereLengthIsBetween8and32_shouldSetValue(String username) {
            Username result = new Username(username);
            Assertions.assertThat(result.value()).isEqualTo(username);
        }
    }

    @Nested

    class PatternValidation {
        @ParameterizedTest
        @ValueSource(strings = {
                "*", // symbols
                "あ", // Japanese
        })
        void username_whereUnallowedPatternsAreUsed_shouldThrowException(String unallowedCharacters) {
            Assertions.assertThatExceptionOfType(DomainValidationException.class)
                    .isThrownBy(() -> {
                        Username.create("value" + unallowedCharacters);
                    });
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "abcdefgh", // lowercase alphabet only
                "ABCDEFGH", // uppercase alphabet only
                "12345678", // numbers only
                "--------", // hyphens only
                "abcABC123-", // mix of all
        })
        void username_whereOnlyAllowedPatternsAreUsed_shouldSetValue(String username) throws UsernameLengthException, UsernamePatternException {
            Username result = Username.create(username);
            Assertions.assertThat(result.value()).isEqualTo(username);
        }
    }

}
