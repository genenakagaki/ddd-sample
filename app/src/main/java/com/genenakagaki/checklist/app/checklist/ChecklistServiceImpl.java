package com.genenakagaki.checklist.app.checklist;

import com.genenakagaki.checklist.domain.checklist.Checklist;
import com.genenakagaki.checklist.domain.checklist.ChecklistId;
import com.genenakagaki.checklist.domain.checklist.ChecklistItemId;
import com.genenakagaki.checklist.domain.checklist.ChecklistMemberRole;
import com.genenakagaki.checklist.domain.checklist.error.ChecklistItemNotFoundException;
import com.genenakagaki.checklist.domain.checklist.error.DuplicateUserGroupMemberException;
import com.genenakagaki.checklist.domain.checklist.error.UserNotChecklistMemberException;
import com.genenakagaki.checklist.domain.error.EntityNotFoundException;
import com.genenakagaki.checklist.domain.user.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChecklistServiceImpl implements ChecklistService {

    private final ChecklistRepository checklistRepository;

    @Transactional
    @Override
    public void create(UserId actor, String name) {
        Checklist checklist = Checklist.create(actor, name);
        checklistRepository.save(checklist);
    }

    @Transactional
    @Override
    public void changeName(ChecklistId checklistId, UserId actor, String name) {
        Checklist checklist = getChecklist(checklistId);
        checklist.changeName(actor, name);
        checklistRepository.save(checklist);
    }

    @Transactional
    @Override
    public void changeItemDescription(ChecklistId checklistId, ChecklistItemId itemId, UserId actor, String description) throws ChecklistItemNotFoundException {
        Checklist checklist = getChecklist(checklistId);
        checklist.changeItemDescription(actor, itemId, description);
        checklistRepository.save(checklist);
    }

    @Transactional
    @Override
    public void toggleItemState(ChecklistId checklistId, ChecklistItemId itemId, UserId actor) throws ChecklistItemNotFoundException {
        Checklist checklist = getChecklist(checklistId);
        checklist.toggleItemState(actor, itemId);
        checklistRepository.save(checklist);
    }

    @Transactional
    @Override
    public void unassignItem(ChecklistId checklistId, ChecklistItemId itemId, UserId actor) throws ChecklistItemNotFoundException {
        Checklist checklist = getChecklist(checklistId);
        checklist.unassignItem(actor, itemId);
        checklistRepository.save(checklist);
    }

    @Transactional
    @Override
    public void assignItem(ChecklistId checklistId, ChecklistItemId itemId, UserId actor, UserId assignee) throws ChecklistItemNotFoundException, UserNotChecklistMemberException {
        Checklist checklist = getChecklist(checklistId);
        checklist.assignItem(actor, itemId, assignee);
        checklistRepository.save(checklist);
    }

    @Transactional
    @Override
    public void addMember(ChecklistId checklistId, UserId actor, UserId memberUserId) throws DuplicateUserGroupMemberException {
        Checklist checklist = getChecklist(checklistId);
        checklist.addMember(actor, memberUserId);
        checklistRepository.save(checklist);
    }

    @Transactional
    @Override
    public void removeMember(ChecklistId checklistId, UserId actor, UserId memberUserId) throws UserNotChecklistMemberException {
        Checklist checklist = getChecklist(checklistId);
        checklist.removeMember(actor, memberUserId);
        checklistRepository.save(checklist);
    }

    @Transactional
    @Override
    public void assignMemberRole(ChecklistId checklistId, UserId actor, UserId memberUserId, ChecklistMemberRole role) throws UserNotChecklistMemberException {
        Checklist checklist = getChecklist(checklistId);
        checklist.assignMemberRole(actor, memberUserId, role);
        checklistRepository.save(checklist);
    }

    private Checklist getChecklist(ChecklistId checklistId) {
        return checklistRepository.findById(checklistId).orElseThrow(() -> new EntityNotFoundException());
    }

}