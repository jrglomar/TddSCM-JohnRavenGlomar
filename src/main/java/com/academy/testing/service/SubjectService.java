package com.academy.testing.service;

import com.academy.testing.exceptions.RecordNotFoundException;
import com.academy.testing.model.Subject;

import java.util.List;

public interface SubjectService {
    Iterable<Subject> findAllSubject();

    Subject findSubjectById(Long id) throws RecordNotFoundException;

    Subject saveSubject(Subject subject);

    List<Subject> findSubjectByStatus(String status);
}
