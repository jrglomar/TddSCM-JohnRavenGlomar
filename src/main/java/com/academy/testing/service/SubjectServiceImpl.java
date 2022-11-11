package com.academy.testing.service;

import com.academy.testing.exceptions.RecordNotFoundException;
import com.academy.testing.model.Subject;
import com.academy.testing.repository.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public Iterable<Subject> findAllSubject() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject findSubjectById(Long id) throws RecordNotFoundException {
        return subjectRepository.findById(id).orElseThrow(RecordNotFoundException::new);
    }

    @Override
    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public Subject updateSubject(Subject subject, Long id) throws RecordNotFoundException {
        Subject subjectFound = subjectRepository.findById(id).orElseThrow(RecordNotFoundException::new);
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(subject, subjectFound);
        return subjectFound;
    }

    @Override
    public void deleteSubject(Long id) throws RecordNotFoundException {
        subjectRepository.delete(subjectRepository.findById(id).orElseThrow(RecordNotFoundException::new));
    }
}
