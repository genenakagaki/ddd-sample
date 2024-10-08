package com.genenakagaki.checklist.app.user;

import com.genenakagaki.checklist.domain.user.error.DuplicateUsernameException;
import com.genenakagaki.checklist.domain.user.Password;
import com.genenakagaki.checklist.domain.user.Username;

import java.util.function.Function;

public interface UserService {
    void register(Username username, Password password, Function<String, String> encodePassword) throws DuplicateUsernameException;
}
