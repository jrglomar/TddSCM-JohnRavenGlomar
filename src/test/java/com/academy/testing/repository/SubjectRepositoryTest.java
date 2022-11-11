package com.academy.testing.repository;

import com.academy.testing.model.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class SubjectRepositoryTest {

    @Autowired
    private SubjectRepository subjectRepository;

    @Test
    public void save(){
        // arrange
        Subject newSubject = new Subject();
        newSubject.setTitle("title_new");

        // act
        Subject savedSubject = subjectRepository.save(newSubject);

        // assert
        assertEquals("title_new", newSubject.getTitle());
        assertNotNull(savedSubject.getId());
    }

    @Test
    public void findById(){
        // arrange
        Subject newSubject = new Subject();
        newSubject.setTitle("title_new");
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
        Subject newSubject1 = new Subject();
        Subject newSubject2 = new Subject();
        newSubject1.setTitle("new_title1");
        newSubject2.setTitle("new_title2");

        // act
        Iterable<Subject> subjectList = subjectRepository.saveAll(Arrays.asList(newSubject1, newSubject2));

        // assert
        assertThat(subjectRepository.findAll()).containsAll(subjectList);
    }


}