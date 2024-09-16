package com.genenakagaki.checklist.domain.user;

import com.genenakagaki.checklist.domain.user.error.PasswordLengthException;
import com.genenakagaki.checklist.domain.user.error.PasswordPatternException;

import java.util.regex.Pattern;

public record Password(String value) {
    public static int MIN_LENGTH = 8;
    public static int MAX_LENGTH = 32;

    public static Password create(String password) throws PasswordLengthException, PasswordPatternException {
        if (!(MIN_LENGTH <= password.length() && password.length() <= MAX_LENGTH)) {
            throw new PasswordLengthException();
        }

        if (!Pattern.matches("^[a-zA-Z0-9!@#$%^&*]*$", password)) {
            throw new PasswordPatternException();
        }

        return new Password(password);
    }
}
