package com.genenakagaki.checklist.app;

import com.genenakagaki.checklist.domain.UserEntity;
import com.genenakagaki.checklist.domain.user.DuplicateUsernameException;

public interface UserRepository {

    void save(UserEntity.Data userData) throws DuplicateUsernameException;
}
