package com.genenakagaki.checklist.domain.checklist;

import com.genenakagaki.checklist.domain.Entity;
import com.genenakagaki.checklist.domain.user.UserId;
import lombok.Getter;

@Getter
public class ChecklistMember extends Entity<UserId> {

    private ChecklistMemberRole role;

    public ChecklistMember(UserId id, ChecklistMemberRole role) {
        super(id);
        this.role = role;
    }

    static ChecklistMember create(UserId userId) {
        return new ChecklistMember(userId, ChecklistMemberRole.GUEST);
    }

    void assignRole(ChecklistMemberRole role) {
        this.role = role;
    }
}
