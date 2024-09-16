package com.genenakagaki.checklist.domain.checklist;

import com.genenakagaki.checklist.domain.Base64UUID;
import com.genenakagaki.checklist.domain.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = false)
@Value
public class ChecklistItemId extends ValueObject {
    Base64UUID id;

    public String getIdString() {
        return id.value();
    }
}
