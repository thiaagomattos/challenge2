package thiago.silveira.demo.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import thiago.silveira.demo.dtos.StudentDtoRequest;
import thiago.silveira.demo.dtos.StudentDtoResponse;
import thiago.silveira.demo.entity.Student;
import thiago.silveira.demo.exceptions.StudentIncorrectFieldException;
import thiago.silveira.demo.service.StudentService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    private StudentDtoRequest studentDtoRequest;

    @BeforeEach
    public void setUp() {
        studentDtoRequest = new StudentDtoRequest();
        studentDtoRequest.setFirstName("Lucas");
        studentDtoRequest.setLastName("Silva");
        studentDtoRequest.setEmail("lucas@gmail.com");
        studentDtoRequest.setAddress("Av. Machado, 992");
        studentDtoRequest.setClassroom_id(1L);
    }

    @Test
    public void testPostEndpoint() throws Exception {
        doNothing().when(studentService).save(any(StudentDtoRequest.class));

        mockMvc.perform(post("/v1/scholarship/student/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"1\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Student saved!"));
    }

    @Test
    public void testGetEndpoint() throws Exception {
        StudentDtoResponse studentDtoResponse = new StudentDtoResponse();
        studentDtoResponse.setId(1L);
        studentDtoResponse.setFirstName("Lucas");
        studentDtoResponse.setLastName("Silva");
        studentDtoResponse.setEmail("lucas@gmail.com");
        studentDtoResponse.setAddress("Av. Machado, 992");


        when(studentService.getById(1L)).thenReturn(studentDtoResponse);

        mockMvc.perform(get("/v1/scholarship/student/get/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Lucas"))
                .andExpect(jsonPath("$.lastName").value("Silva"))
                .andExpect(jsonPath("$.email").value("lucas@gmail.com"))
                .andExpect(jsonPath("$.address").value("Av. Machado, 992"));
    }

    @Test
    public void testUpdateEndpoint() throws Exception {
        Student studentToUpdate = new Student();
        studentToUpdate.setId(1L);
        studentToUpdate.setFirstName("Lucas");
        studentToUpdate.setLastName("Silva");
        studentToUpdate.setEmail("lucas@gmail.com");
        studentToUpdate.setAddress("Rua João Meirelles, 433");

        when(studentService.updateStudent(any(Student.class))).thenReturn(studentToUpdate);

        mockMvc.perform(put("/v1/scholarship/student/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"firstName\": Lucas, \"lastName\": Silva, \"email\": lucas@gmail.com," +
                                "\"address\": Rua João Meirelles, 433\"}"))  // Provide valid JSON content
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Lucas"))
                .andExpect(jsonPath("$.lastName").value("Silva"))
                .andExpect(jsonPath("$.email").value("lucas@gmail.com"))
                .andExpect(jsonPath("$.address").value("Rua João Meirelles, 433"));

    }

    @Test
    public void testDeleteEndpoint() throws Exception {
        Long studentIdToDelete = 1L;

        mockMvc.perform(delete("/v1/scholarship/student/delete")
                        .param("id", String.valueOf(studentIdToDelete)))
                .andExpect(status().isOk())
                .andExpect(content().string("Student Deleted!"));

        verify(studentService, times(1)).deleteStudent(studentIdToDelete);
    }
}