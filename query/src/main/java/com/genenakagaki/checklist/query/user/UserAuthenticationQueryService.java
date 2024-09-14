package com.genenakagaki.checklist.query.user;

import java.util.Optional;

public interface UserAuthenticationQueryService {
    Optional<UserAuthenticationQuery> find(String username);
}
