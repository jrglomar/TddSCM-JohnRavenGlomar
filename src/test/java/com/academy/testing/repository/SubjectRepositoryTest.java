package com.academy.testing.repository;

import com.academy.testing.model.Subject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class SubjectRepositoryTest {

    @Autowired
    private SubjectRepository subjectRepository;

    @Test
    public void findByStatus(){
        // arrange
        Subject newSubject1 = new Subject(1L, "title_new1", "Active");
        Subject newSubject2 = new Subject(2L, "title_new2", "Inactive");
        Subject newSubject3 = new Subject(3L, "title_new3", "Active");

        // act
        subjectRepository.saveAll(Arrays.asList(newSubject1, newSubject2, newSubject3));
        List<Subject> resultSubjectList = subjectRepository.findByStatus("Active");

        // assert
        assertThat(resultSubjectList).contains(newSubject1, newSubject3);
    }

    @Test
    public void save(){
        // arrange
        Subject newSubject = new Subject(1L, "title_new", "Active");

        // act
        Subject savedSubject = subjectRepository.save(newSubject);

        // assert
        assertEquals("title_new", newSubject.getTitle());
        assertNotNull(savedSubject.getId());
    }

    @Test
    public void findById(){
        // arrange
        Subject newSubject = new Subject(1L, "title_new", "Active");
        Subject savedSubject = subjectRepository.save(newSubject);

        // act
        Optional<Subject> existingSubject = subjectRepository.findById(savedSubject.getId());

        // assert
        assertTrue(existingSubject.isPresent());
        assertEquals("title_new", existingSubject.get().getTitle());
    }

    @Test
    public void findAll(){
        // arrange
        Subject newSubject1 = new Subject(1L, "title_new1", "Active");
        Subject newSubject2 = new Subject(2L, "title_new2", "Inactive");

        // act
        Iterable<Subject> subjectList = subjectRepository.saveAll(Arrays.asList(newSubject1, newSubject2));

        // assert
        assertThat(subjectRepository.findAll()).containsAll(subjectList);
    }



}