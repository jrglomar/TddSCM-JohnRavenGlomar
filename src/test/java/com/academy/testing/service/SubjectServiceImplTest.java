package com.academy.testing.service;

import com.academy.testing.exceptions.RecordNotFoundException;
import com.academy.testing.model.Subject;
import com.academy.testing.repository.SubjectRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SubjectServiceImplTest {

    @Mock
    SubjectRepository subjectRepository;

    @InjectMocks
    SubjectServiceImpl subjectService;

    @Test
    @DisplayName("GIVEN newSubject1, newSubject3 " +
            "WHEN findByStatus is executed passing Active " +
            "THEN result should contains the new subject 1, 3"
    )
    public void findSubjectByStatus(){
        // arrange
        Subject newSubject1 = new Subject(1L, "title_new1", "Active");
        Subject newSubject3 = new Subject(3L, "title_new3", "Active");

        // act
        List<Subject> expectedSubjectsList = List.of(newSubject1, newSubject3);
        Mockito.when(subjectRepository.findByStatus(Mockito.anyString())).thenReturn(expectedSubjectsList);

        List<Subject> result = subjectService.findSubjectByStatus("Active");
        Mockito.verify(subjectRepository).findByStatus("Active");

        // assert
        assertThat(result).contains(newSubject1, newSubject3);
    }

    @Test
    @DisplayName("GIVEN newSubject1 and newSubject2 " +
            "WHEN findAll is executed " +
            "THEN result should contains newSubject1 and newSubject2"
    )
    public void findAllSubject(){
        // arrange
        Subject newSubject1 = new Subject(1L, "title_new1", "Active");
        Subject newSubject2 = new Subject(2L, "title_new2", "Inactive");

        List<Subject> expectedSubjectsList = List.of(newSubject1, newSubject2);
        Mockito.when(subjectRepository.findAll()).thenReturn(expectedSubjectsList);
        Mockito.verify(subjectRepository).findAll();

        // act
        Iterable<Subject> subjectList = subjectService.findAllSubject();

        // assert
        assertThat(subjectList).containsAll(expectedSubjectsList);
    }

    @Test
    @DisplayName("GIVEN expectedSubject " +
            "WHEN saveSubject is executed passing expected subject " +
            "THEN result should be equal to expected subject "
    )
    void saveSubject(){
        // arrange
        Subject expectedSubject = new Subject(1L, "title_new1", "Active");

        Mockito.when(subjectRepository.save(Mockito.any(Subject.class))).thenReturn(expectedSubject);

        // act
        Subject subject = subjectService.saveSubject(expectedSubject);
        Mockito.verify(subjectRepository).save(expectedSubject);

        // assert
        assertEquals(expectedSubject, subject);
    }

    @Test
    @DisplayName("GIVEN expectedSubject " +
            "WHEN findSubjectById is executed passing 1 as id" +
            "THEN result should be equal to expected subject "
    )
    void findSubjectById() throws RecordNotFoundException {
        // arrange
        Subject expectedSubject = new Subject(1L, "title_new1", "Active");
        Mockito.when(subjectRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(expectedSubject));

        // act
        Subject subject = subjectService.findSubjectById(1L);
        Mockito.verify(subjectRepository).findById(1L);

        // assert
        assertEquals(expectedSubject, subject);
    }



}