package com.academy.testing.repository;

import com.academy.testing.model.Subject;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("GIVEN subject data " +
            "WHEN findStatus is executed passing Active as params" +
            "THEN result should have subject 1 and 3"
    )
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
    @DisplayName("GIVEN newSubject " +
            "WHEN save is executed passing newSubject as params" +
            "THEN result should have an id and equals to newSubject"
    )
    public void save(){
        // arrange
        Subject newSubject = new Subject(1L, "title_new", "Active");

        // act
        Subject savedSubject = subjectRepository.save(newSubject);

        // assert
        assertEquals(newSubject, savedSubject);
        assertNotNull(savedSubject.getId());
    }

    @Test
    @DisplayName("GIVEN newSubject" +
            "WHEN findById is executed passing the savedSubject" +
            "THEN result should find the existing subject and it should be equals"
    )
    public void findById(){
        // arrange
        Subject newSubject = new Subject(1L, "title_new", "Active");
        Subject savedSubject = subjectRepository.save(newSubject);

        // act
        Optional<Subject> existingSubject = subjectRepository.findById(savedSubject.getId());

        // assert
        assertTrue(existingSubject.isPresent());
        assertEquals(newSubject, savedSubject);
    }

    @Test
    @DisplayName("GIVEN newSubject1 and newSubject2" +
            "WHEN find all is executed after saving the subjects data" +
            "THEN result should contains all of the subjects data"
    )
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