package com.genenakagaki.checklist.query.user;

import com.genenakagaki.checklist.domain.Base64UUID;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.genenakagaki.checklist.persistence.generated.Tables.USER;

@Service
@RequiredArgsConstructor
public class UserAuthenticationQueryServiceImpl implements UserAuthenticationQueryService {

    private final DSLContext dsl;

    @Override
    public Optional<UserAuthenticationQuery> find(String username) {
        return dsl.select().from(USER)
                .where(USER.USERNAME.eq(username))
                .fetchOptionalInto(USER)
                .map(user -> new UserAuthenticationQuery(new Base64UUID(user.getUserId()),
                        username,
                        user.getEncodedPassword()));
    }
}
