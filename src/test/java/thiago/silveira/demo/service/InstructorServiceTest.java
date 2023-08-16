package thiago.silveira.demo.service;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import thiago.silveira.demo.dtos.InstructorDtoRequest;
import thiago.silveira.demo.dtos.InstructorDtoResponse;
import thiago.silveira.demo.entity.Instructor;
import thiago.silveira.demo.repository.InstructorRepository;

import static org.junit.jupiter.api.Assertions.*;

public class InstructorServiceTest {

    @Mock
    private InstructorRepository instructorRepository;

    @InjectMocks
    private InstructorService instructorService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveInstructor() {
        InstructorDtoRequest request = new InstructorDtoRequest();
        Instructor expectedInstructor = new Instructor(1L, "Samuel", "Prado", "samuel@gmail.com", "Rua dos Golfinos, 522");

        when(instructorRepository.save(any(Instructor.class))).thenReturn(expectedInstructor);

        Instructor savedInstructor = instructorService.save(request);

        assertEquals(expectedInstructor, savedInstructor);
    }

    @Test
    public void testGetInstructorById() {
        Long instructorId = 1L;
        Instructor instructor = new Instructor();
        when(instructorRepository.findById(instructorId)).thenReturn(java.util.Optional.of(instructor));

        InstructorDtoResponse result = instructorService.getById(instructorId);

        assertNotNull(result);
        assertEquals(instructorId, result.getId());
        verify(instructorRepository, times(1)).findById(instructorId);
    }

    @Test
    public void testUpdateInstructor() {
        Instructor instructorToUpdate = new Instructor();
        when(instructorRepository.save(any())).thenReturn(instructorToUpdate);

        Instructor result = instructorService.updateInstructor(instructorToUpdate);

        assertNotNull(result);
        assertEquals(instructorToUpdate, result);
        verify(instructorRepository, times(1)).save(instructorToUpdate);
    }

    @Test
    public void testDeleteInstructor() {
        Long instructorId = 1L;

        instructorService.deleteInstructor(instructorId);

        verify(instructorRepository, times(1)).deleteById(instructorId);
    }
}