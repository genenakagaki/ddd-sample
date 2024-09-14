package com.genenakagaki.checklist.query.user;

import com.genenakagaki.checklist.domain.Base64UUID;
import com.genenakagaki.checklist.domain.user.UserId;
import com.genenakagaki.checklist.domain.user.Username;

public record UserAuthenticationQuery(UserId userId, Username username, String encodedPassword) {

    public UserAuthenticationQuery(Base64UUID userId, String username, String encodedPassword) {
        this(new UserId(userId), new Username(username), encodedPassword);
    }
}
