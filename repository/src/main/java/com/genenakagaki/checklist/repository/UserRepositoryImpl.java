package com.genenakagaki.checklist.repository;

import com.genenakagaki.checklist.app.UserRepository;
import com.genenakagaki.checklist.domain.UserEntity;
import com.genenakagaki.checklist.domain.user.DuplicateUsernameException;
import com.genenakagaki.checklist.persistence.generated.tables.records.UserRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import static com.genenakagaki.checklist.persistence.generated.Tables.USER;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final DSLContext dsl;

    @Override
    public void save(UserEntity.Data userData) throws DuplicateUsernameException {
        try {
            UserRecord user = dsl.newRecord(USER);
            user.setUserId(userData.userId().userId().uuid());
            user.setUsername(userData.username().value());
            user.setEncodedPassword(userData.encodedPassword());
            user.store();
        } catch (DuplicateKeyException e) {
            throw new DuplicateUsernameException();
        }
    }

}
