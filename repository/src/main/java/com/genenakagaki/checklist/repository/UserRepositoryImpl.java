package com.genenakagaki.checklist.repository;

import com.genenakagaki.checklist.app.user.UserRepository;
import com.genenakagaki.checklist.domain.UserEntity;
import com.genenakagaki.checklist.domain.user.error.DuplicateUsernameException;
import com.genenakagaki.checklist.persistence.generated.tables.records.UserRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static com.genenakagaki.checklist.persistence.generated.Tables.USER;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final DSLContext dsl;

    @Transactional
    @Override
    public void save(UserEntity.Data userData) throws DuplicateUsernameException {
        try {
            UserRecord user = dsl.newRecord(USER);
            user.setUserId(userData.userId().userId().value());
            user.setUsername(userData.username().value());
            user.setEncodedPassword(userData.encodedPassword());
            user.store();
        } catch (DuplicateKeyException e) {
            throw new DuplicateUsernameException();
        }
    }

}
