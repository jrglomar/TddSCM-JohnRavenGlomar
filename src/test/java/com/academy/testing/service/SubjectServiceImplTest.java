package com.academy.testing.service;

import com.academy.testing.exceptions.RecordNotFoundException;
import com.academy.testing.model.Subject;
import com.academy.testing.repository.SubjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SubjectServiceImplTest {

    @Mock
    SubjectRepository subjectRepository;

    @InjectMocks
    SubjectServiceImpl subjectService;

    @Test
    public void findSubjectByStatus(){
        // arrange
        Subject newSubject1 = new Subject(1L, "title_new1", "Active");
        Subject newSubject2 = new Subject(2L, "title_new2", "Inactive");
        Subject newSubject3 = new Subject(3L, "title_new3", "Active");

        // act

        // assert
    }

    @Test
    public void findAllSubject(){
        // arrange
        Subject newSubject1 = new Subject(1L, "title_new1", "Active");
        Subject newSubject2 = new Subject(2L, "title_new2", "Inactive");

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
        Subject expectedSubject = new Subject(1L, "title_new1", "Active");

        Mockito.when(subjectRepository.save(Mockito.any(Subject.class))).thenReturn(expectedSubject);

        // act
        Subject subject = subjectService.saveSubject(expectedSubject);

        // assert
        assertEquals(expectedSubject, subject);
    }

    @Test
    void findSubjectById() throws RecordNotFoundException {
        // arrange
        Subject expectedSubject = new Subject(1L, "title_new1", "Active");
        Mockito.when(subjectRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(expectedSubject));

        // act
        Subject subject = subjectService.findSubjectById(1L);

        // asser
        assertEquals(expectedSubject, subject);
    }



}