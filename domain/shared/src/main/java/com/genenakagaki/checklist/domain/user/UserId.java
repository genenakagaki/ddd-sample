package com.genenakagaki.checklist.domain.user;

import com.genenakagaki.checklist.domain.Base64UUID;

public record UserId(Base64UUID userId) {

    public String getIdString() {
        return userId.uuid();
    }
}
