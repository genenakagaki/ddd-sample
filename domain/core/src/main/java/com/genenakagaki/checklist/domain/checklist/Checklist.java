package com.genenakagaki.checklist.domain.checklist;

import com.genenakagaki.checklist.domain.AggregateRoot;
import com.genenakagaki.checklist.domain.Base64UUID;
import com.genenakagaki.checklist.domain.checklist.error.ChecklistItemNotFoundException;
import com.genenakagaki.checklist.domain.checklist.error.DuplicateUserGroupMemberException;
import com.genenakagaki.checklist.domain.checklist.error.UserNotChecklistMemberException;
import com.genenakagaki.checklist.domain.error.UnauthorizedException;
import com.genenakagaki.checklist.domain.user.UserId;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Getter
public class Checklist extends AggregateRoot<ChecklistId> {

    private String name;
    private List<ChecklistItem> items;
    private List<ChecklistMember> members;

    public Checklist(ChecklistId id, String name, List<ChecklistItem> items, List<ChecklistMember> checklistMembers) {
        super(id);
        this.name = name;
        this.items = items;
        this.members = checklistMembers;
    }

    public static Checklist create(UserId actor, String name) {
        return new Checklist(new ChecklistId(Base64UUID.create()),
                name,
                new ArrayList<>(),
                new ArrayList<>(List.of(new ChecklistMember(actor, ChecklistMemberRole.OWNER))));
    }

    private Optional<ChecklistMember> getMember(UserId userId) {
        return members.stream()
                .filter(m -> m.id().equals(userId))
                .findFirst();
    }

    private void authorize(UserId actor, ChecklistMemberRole... authorizedRoles) {
        getMember(actor)
                .filter(member -> Arrays.stream(authorizedRoles).anyMatch(role -> role == member.role()))
                .orElseThrow(() -> new UnauthorizedException());
    }

    public void changeName(UserId actor, String name) {
        authorize(actor, ChecklistMemberRole.OWNER);
        this.name = name;
    }

    private ChecklistItem getItem(ChecklistItemId itemId) throws ChecklistItemNotFoundException {
        return this.items.stream()
                .filter(item -> item.id().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new ChecklistItemNotFoundException());
    }

    public void changeItemDescription(UserId actor, ChecklistItemId itemId, String description) throws ChecklistItemNotFoundException {
        authorize(actor, ChecklistMemberRole.OWNER, ChecklistMemberRole.GUEST);
        ChecklistItem item = getItem(itemId);
        item.changeDescription(description);
    }

    public void toggleItemState(UserId actor, ChecklistItemId itemId) throws ChecklistItemNotFoundException {
        authorize(actor, ChecklistMemberRole.OWNER, ChecklistMemberRole.GUEST);
        ChecklistItem item = getItem(itemId);
        item.toggleState();
    }

    public void unassignItem(UserId actor, ChecklistItemId itemId) throws ChecklistItemNotFoundException {
        authorize(actor, ChecklistMemberRole.OWNER, ChecklistMemberRole.GUEST);
        ChecklistItem item = getItem(itemId);
        item.unassign();
    }

    public void assignItem(UserId actor, ChecklistItemId itemId, UserId assignee) throws ChecklistItemNotFoundException, UserNotChecklistMemberException {
        authorize(actor, ChecklistMemberRole.OWNER, ChecklistMemberRole.GUEST);
        ChecklistItem item = getItem(itemId);
        getMember(assignee).orElseThrow(() -> new UserNotChecklistMemberException());
        item.assign(assignee);
    }

    public void addMember(UserId actor, UserId memberUserId) throws DuplicateUserGroupMemberException {
        authorize(actor, ChecklistMemberRole.OWNER);
        if (members.stream().anyMatch(m -> m.id().equals(memberUserId))) {
            throw new DuplicateUserGroupMemberException();
        }
        members.add(ChecklistMember.create(memberUserId));
    }

    public void removeMember(UserId actor, UserId memberUserId) throws UserNotChecklistMemberException {
        authorize(actor, ChecklistMemberRole.OWNER);
        if (members.stream().noneMatch(m -> m.id().equals(memberUserId))) {
            throw new UserNotChecklistMemberException();
        }
        this.members = members.stream().filter(m -> !m.id().equals(memberUserId)).toList();
    }

    public void assignMemberRole(UserId actor, UserId memberUserId, ChecklistMemberRole role) throws UserNotChecklistMemberException {
        authorize(actor, ChecklistMemberRole.OWNER);
        ChecklistMember member = getMember(memberUserId).orElseThrow(() -> new UserNotChecklistMemberException());
        member.assignRole(role);
    }
}
