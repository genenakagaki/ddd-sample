package com.genenakagaki.checklist.app.user;

import com.genenakagaki.checklist.domain.UserEntity;
import com.genenakagaki.checklist.domain.user.error.DuplicateUsernameException;

public interface UserRepository {

    void save(UserEntity.Data userData) throws DuplicateUsernameException;
}
