package com.bagus2x.serverapp.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentRepository.deleteAll();
    }

    @Test
    void testSave() {
        Student student = new Student();
        student.setName("john");
        student.setEmail("john@gmail.com");

        studentRepository.save(student);

        assertNotNull(student.getId());
        assertEquals("john", student.getName());
        assertEquals("john@gmail.com", student.getEmail());
    }

    @Test
    void testSaveShouldThrowErrorIfNameIsNull() {
        Student student = new Student();
        student.setEmail("john@gmail.com");

        assertThrows(DataIntegrityViolationException.class, () -> studentRepository.save(student));
    }

    @Test
    void testSaveShouldThrowErrorIfEmailIsNull() {
        Student student = new Student();
        student.setName("john");

        assertThrows(DataIntegrityViolationException.class, () -> studentRepository.save(student));
    }

    @Test
    void testSaveShouldThrowErrorIfEmailIsDuplicate() {
        Student harryMaguire = new Student();
        harryMaguire.setName("Harry Maguire");
        harryMaguire.setEmail("harry@gmail.com");

        studentRepository.save(harryMaguire);

        assertNotNull(harryMaguire.getId());

        Student harryKane = new Student();
        harryKane.setName("Harry Kane");
        harryKane.setEmail("harry@gmail.com");

        assertThrows(DataIntegrityViolationException.class, () -> studentRepository.save(harryKane));
    }
}