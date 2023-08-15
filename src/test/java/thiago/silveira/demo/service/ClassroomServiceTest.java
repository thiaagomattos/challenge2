package thiago.silveira.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import thiago.silveira.demo.dtos.ClassroomDtoRequest;
import thiago.silveira.demo.dtos.StudentDtoRequest;
import thiago.silveira.demo.entity.Classroom;
import thiago.silveira.demo.entity.Status;
import thiago.silveira.demo.entity.Student;
import thiago.silveira.demo.exceptions.ClassroomIncorrectFieldException;
import thiago.silveira.demo.repository.ClassroomRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static thiago.silveira.demo.entity.Status.STARTED;

public class ClassroomServiceTest {

    @InjectMocks
    private ClassroomService classroomService;

    @Mock
    private ClassroomRepository classroomRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveClassroom() {
        ClassroomDtoRequest dtoRequest = new ClassroomDtoRequest();
        dtoRequest.setNumberOfStudents(20);
        dtoRequest.setNumberOfCoordinators(1);
        dtoRequest.setNumberOfInstructors(3);
        dtoRequest.setNumberOfScrumMasters(1);
        dtoRequest.setStatus(STARTED);
        dtoRequest.setDiscipline("Computer Science");
        List<StudentDtoRequest> studentDtos = new ArrayList<>();
        studentDtos.add(new StudentDtoRequest());
        dtoRequest.setStudents(studentDtos);

        Classroom classroom = new Classroom();
        classroom.setNumberOfStudents(dtoRequest.getNumberOfStudents());
        classroom.setNumberOfCoordinators(dtoRequest.getNumberOfCoordinators());
        classroom.setNumberOfInstructors(dtoRequest.getNumberOfInstructors());
        classroom.setNumberOfScrumMasters(dtoRequest.getNumberOfScrumMasters());
        classroom.setStatus(dtoRequest.getStatus());
        classroom.setDiscipline(dtoRequest.getDiscipline());
        List<Student> students = new ArrayList<>();
        for (StudentDtoRequest student : studentDtos) {
            Student student1 = new Student();
            student1.setFirstName(student.getFirstName());
            student1.setLastName(student.getLastName());
            student1.setEmail(student.getEmail());
            student1.setAddress(student.getAddress());
            student1.setClassroom(classroom);
            students.add(student1);
        }
        classroom.setStudents(students);

        when(classroomRepository.save(any())).thenReturn(classroom);

        Classroom savedClassroom = classroomService.save(dtoRequest);

        assertNotNull(savedClassroom);
        assertEquals(dtoRequest.getStatus(), savedClassroom.getStatus());
        assertEquals(dtoRequest.getDiscipline(), savedClassroom.getDiscipline());
        assertEquals(dtoRequest.getNumberOfStudents(), savedClassroom.getNumberOfStudents());
        assertEquals(dtoRequest.getNumberOfCoordinators(), savedClassroom.getNumberOfCoordinators());
        assertEquals(dtoRequest.getNumberOfInstructors(), savedClassroom.getNumberOfInstructors());
        assertEquals(dtoRequest.getNumberOfScrumMasters(), savedClassroom.getNumberOfScrumMasters());
    }

    @Test
    void testGetById() {
        Long classroomId = 1L;

        Classroom classroom = new Classroom();
        classroom.setId(classroomId);

        when(classroomRepository.findById(classroomId)).thenReturn(Optional.of(classroom));

        Classroom retrievedClassroom = classroomService.getById(classroomId);

        assertNotNull(retrievedClassroom);
        assertEquals(classroomId, retrievedClassroom.getId());
    }

    @Test
    void testUpdateClassroom() {
        Long classroomId = 1L;
        Integer classroomNumberOfStudents = 15;
        Integer classroomNumberOfCoordinators = 1;
        Integer classroomNumberOfInstructors = 3;
        Integer classroomNumberOfScrumMasters = 1;
        Status classroomStatus = STARTED;
        String classroomDiscipline = "English";
        Classroom classroom = new Classroom();
        classroom.setId(classroomId);
        classroom.setNumberOfStudents(classroomNumberOfStudents);
        classroom.setNumberOfCoordinators(classroomNumberOfCoordinators);
        classroom.setNumberOfInstructors(classroomNumberOfInstructors);
        classroom.setNumberOfScrumMasters(classroomNumberOfScrumMasters);
        classroom.setStatus(classroomStatus);
        classroom.setDiscipline(classroomDiscipline);
        when(classroomRepository.save(any())).thenReturn(classroom);

        Classroom updatedClassroom = classroomService.updateClassroom(classroom);

        assertNotNull(updatedClassroom);
        assertEquals(classroomId, updatedClassroom.getId());
        assertEquals(classroomNumberOfStudents, updatedClassroom.getNumberOfStudents());
        assertEquals(classroomNumberOfCoordinators, updatedClassroom.getNumberOfCoordinators());
        assertEquals(classroomNumberOfInstructors, updatedClassroom.getNumberOfInstructors());
        assertEquals(classroomNumberOfScrumMasters, updatedClassroom.getNumberOfScrumMasters());
        assertEquals(classroomStatus, updatedClassroom.getStatus());
        assertEquals(classroomDiscipline, updatedClassroom.getDiscipline());
    }

    @Test
    void testDeleteClassroom() {
        Long classroomId = 1L;
        classroomService.deleteClassroom(classroomId);
        verify(classroomRepository, times(1)).deleteById(classroomId);
    }
}