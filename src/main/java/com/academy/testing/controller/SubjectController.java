package com.academy.testing.controller;

import com.academy.testing.exceptions.RecordNotFoundException;
import com.academy.testing.model.Subject;
import com.academy.testing.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public Iterable<Subject> findAll() {
        return subjectService.findAllSubject();
    }

    @GetMapping("/{id}")
    public Subject findById(@PathVariable Long id) throws RecordNotFoundException {
        return subjectService.findSubjectById(id);
    }

    @PostMapping
    public Subject save(@RequestBody Subject subject) {
        return subjectService.saveSubject(subject);
    }

    @GetMapping("/status/{status}")
    public List<Subject> findByStatus(@PathVariable String status) {
        return subjectService.findSubjectByStatus(status);
    }

}
