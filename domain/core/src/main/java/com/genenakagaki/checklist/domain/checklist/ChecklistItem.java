package com.genenakagaki.checklist.domain.checklist;

import com.genenakagaki.checklist.domain.Base64UUID;
import com.genenakagaki.checklist.domain.Entity;
import com.genenakagaki.checklist.domain.user.UserId;
import lombok.Getter;

import java.util.Optional;

@Getter
public class ChecklistItem extends Entity<ChecklistItemId> {

    private String description;
    private State state;
    private Optional<UserId> assignee;

    public enum State {
        UNCHECKED,
        CHECKED,
    }

    public ChecklistItem(ChecklistItemId id, String description, State state, Optional<UserId> assignee) {
        super(id);
        this.description = description;
        this.state = state;
        this.assignee = assignee;
    }

    static ChecklistItem create(String description) {
        return new ChecklistItem(new ChecklistItemId(Base64UUID.create()), description, State.UNCHECKED, Optional.empty());
    }

    void changeDescription(String description) {
        this.description = description;
    }

    void toggleState() {
        this.state = this.state == State.UNCHECKED ? State.CHECKED : State.UNCHECKED;
    }

    void unassign() {
        this.assignee = Optional.empty();
    }

    void assign(UserId userId) {
        this.assignee = Optional.of(userId);
    }
}
