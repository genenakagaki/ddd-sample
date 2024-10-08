package com.genenakagaki.checklist.domain.user;

import com.genenakagaki.checklist.domain.Base64UUID;
import com.genenakagaki.checklist.domain.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = false)
@Value
public class UserId extends ValueObject {
    Base64UUID userId;

    public String getIdString() {
        return userId.value();
    }
}
