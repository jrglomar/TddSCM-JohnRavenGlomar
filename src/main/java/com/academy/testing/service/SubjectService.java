package com.academy.testing.service;

import com.academy.testing.exceptions.RecordNotFoundException;
import com.academy.testing.model.Subject;

public interface SubjectService {
    Iterable<Subject> findAllSubject();

    Subject findSubjectById(Long id) throws RecordNotFoundException;

    Subject saveSubject(Subject subject);

    Subject updateSubject(Subject subject, Long id) throws RecordNotFoundException;

    void deleteSubject(Long id) throws RecordNotFoundException;
}
