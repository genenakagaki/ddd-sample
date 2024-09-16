package com.genenakagaki.checklist.domain.user;

import com.genenakagaki.checklist.domain.Base64UUID;
import com.genenakagaki.checklist.domain.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = false)
@Value
public class UserGroupId extends ValueObject {
    Base64UUID id;

    public String getIdString() {
        return id.value();
    }
}
