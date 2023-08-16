package thiago.silveira.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import thiago.silveira.demo.dtos.StudentDtoRequest;
import thiago.silveira.demo.dtos.StudentDtoResponse;
import thiago.silveira.demo.entity.Student;
import thiago.silveira.demo.repository.StudentRepository;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveStudent() {
        StudentDtoRequest request = new StudentDtoRequest();
        Student expectedStudent = new Student();

        when(studentRepository.save(any(Student.class))).thenReturn(expectedStudent);

        Student savedStudent = studentService.save(request);

        assertEquals(expectedStudent, savedStudent);
    }

    @Test
    void testGetStudentById() {
        Long studentId = 1L;
        Student student = new Student();
        when(studentRepository.findById(studentId)).thenReturn(java.util.Optional.of(student));

        StudentDtoResponse result = studentService.getById(studentId);

        assertNotNull(result);
        assertEquals(studentId, result.getId());
        verify(studentRepository, times(1)).findById(studentId);
    }

    @Test
    void testUpdateStudent() {
        Student studentToUpdate = new Student();
        when(studentRepository.save(any())).thenReturn(studentToUpdate);

        Student result = studentService.updateStudent(studentToUpdate);

        assertNotNull(result);
        assertEquals(studentToUpdate, result);
        verify(studentRepository, times(1)).save(studentToUpdate);
    }

    @Test
    void testDeleteStudent() {
        Long studentId = 1L;

        studentService.deleteStudent(studentId);

        verify(studentRepository, times(1)).deleteById(studentId);
    }
}