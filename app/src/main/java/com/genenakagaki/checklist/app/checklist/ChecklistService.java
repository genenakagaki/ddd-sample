package com.genenakagaki.checklist.app.checklist;

import com.genenakagaki.checklist.domain.checklist.ChecklistId;
import com.genenakagaki.checklist.domain.checklist.ChecklistItemId;
import com.genenakagaki.checklist.domain.checklist.ChecklistMemberRole;
import com.genenakagaki.checklist.domain.checklist.error.ChecklistItemNotFoundException;
import com.genenakagaki.checklist.domain.checklist.error.DuplicateUserGroupMemberException;
import com.genenakagaki.checklist.domain.checklist.error.UserNotChecklistMemberException;
import com.genenakagaki.checklist.domain.user.UserId;

public interface ChecklistService {
    void create(UserId actor, String name);

    void changeName(ChecklistId checklistId, UserId actor, String name);

    void changeItemDescription(ChecklistId checklistId, ChecklistItemId itemId, UserId actor, String description) throws ChecklistItemNotFoundException;

    void toggleItemState(ChecklistId checklistId, ChecklistItemId itemId, UserId actor) throws ChecklistItemNotFoundException;

    void unassignItem(ChecklistId checklistId, ChecklistItemId itemId, UserId actor) throws ChecklistItemNotFoundException;

    void assignItem(ChecklistId checklistId, ChecklistItemId itemId, UserId actor, UserId assignee) throws ChecklistItemNotFoundException, UserNotChecklistMemberException;

    void addMember(ChecklistId checklistId, UserId actor, UserId memberUserId) throws DuplicateUserGroupMemberException;

    void removeMember(ChecklistId checklistId, UserId actor, UserId memberUserId) throws UserNotChecklistMemberException;

    void assignMemberRole(ChecklistId checklistId, UserId actor, UserId memberUserId, ChecklistMemberRole role) throws UserNotChecklistMemberException;
}
