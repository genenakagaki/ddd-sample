package com.genenakagaki.checklist.domain.user;

import java.util.regex.Pattern;

public record Username(String value) {
    public static int MIN_LENGTH = 8;
    public static int MAX_LENGTH = 32;

    public static Username create(String username) throws UsernameLengthException, UsernamePatternException {
        if (!(MIN_LENGTH <= username.length() && username.length() <= MAX_LENGTH)) {
            throw new UsernameLengthException();
        }

        if (!Pattern.matches("^[a-zA-Z0-9-]*$", username)) {
            throw new UsernamePatternException();
        }

        return new Username(username);
    }
}
