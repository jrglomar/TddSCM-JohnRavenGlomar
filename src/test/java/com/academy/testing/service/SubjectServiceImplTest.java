package com.academy.testing.service;

import com.academy.testing.exceptions.RecordNotFoundException;
import com.academy.testing.model.Subject;
import com.academy.testing.repository.SubjectRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SubjectServiceImplTest {

    @Mock
    SubjectRepository subjectRepository;

    @InjectMocks
    SubjectServiceImpl subjectService;

    @Test
    public void findAllSubject(){
        // arrange
        Subject newSubject1 = new Subject();
        newSubject1.setTitle("name");
        newSubject1.setId(1L);
        Subject newSubject2 = new Subject();
        newSubject2.setTitle("name");
        newSubject1.setId(2L);
        List<Subject> expectedSubjectsList = List.of(newSubject1, newSubject2);
        Mockito.when(subjectRepository.findAll()).thenReturn(expectedSubjectsList);

        // act
        Iterable<Subject> subjectList = subjectService.findAllSubject();

        // assert
        assertThat(subjectList).containsAll(expectedSubjectsList);
    }

    @Test
    void saveSubject(){
        // arrange
        Subject newSubject = new Subject();
        newSubject.setTitle("title_new");
        Subject expectedSubject = new Subject();
        expectedSubject.setTitle("title_new");
        expectedSubject.setId(1L);
        Mockito.when(subjectRepository.save(Mockito.any(Subject.class))).thenReturn(expectedSubject);

        // act
        Subject subject = subjectService.saveSubject(newSubject);

        // assert
        assertEquals("title_new", subject.getTitle());
        assertNotNull(subject.getId());
    }

    @Test
    void findById() throws RecordNotFoundException {
        // arrange
        Subject expectedSubject = new Subject();
        expectedSubject.setTitle("title_new");
        expectedSubject.setId(1L);
        Mockito.when(subjectRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(expectedSubject));

        // act
        Subject subject = subjectService.findSubjectById(1L);

        // asser
        assertEquals("title_new", subject.getTitle());
        assertNotNull(subject.getId());
    }



}