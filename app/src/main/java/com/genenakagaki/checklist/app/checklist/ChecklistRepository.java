package com.genenakagaki.checklist.app.checklist;

import com.genenakagaki.checklist.domain.checklist.Checklist;
import com.genenakagaki.checklist.domain.checklist.ChecklistId;

import java.util.Optional;

public interface ChecklistRepository {

    void save(Checklist checklist);

    Optional<Checklist> findById(ChecklistId checklistId);
}
