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
import thiago.silveira.demo.dtos.InstructorDtoRequest;
import thiago.silveira.demo.dtos.InstructorDtoResponse;
import thiago.silveira.demo.entity.Instructor;
import thiago.silveira.demo.service.InstructorService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(InstructorController.class)
public class InstructorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InstructorService instructorService;

    private InstructorDtoRequest instructorDtoRequest;

    @BeforeEach
    public void setUp() {
        instructorDtoRequest = new InstructorDtoRequest();
        instructorDtoRequest.setFirstName("Lucas");
        instructorDtoRequest.setLastName("Silva");
        instructorDtoRequest.setEmail("lucas@gmail.com");
        instructorDtoRequest.setAddress("Av. Machado, 992");
    }

    @Test
    public void testPostEndpoint() throws Exception {
        InstructorDtoRequest request = new InstructorDtoRequest();
        when(instructorService.save(any())).thenReturn(new Instructor());

        mockMvc.perform(post("/v1/scholarship/instructor/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Instructor saved!"));
    }

    @Test
    public void testGetEndpoint() throws Exception {
        InstructorDtoResponse instructorDtoResponse = new InstructorDtoResponse(1L, "Julio", "Magno", "julio@gmail.com", "Av. Rendeiras, 1221");
        instructorDtoResponse.setId(1L);
        instructorDtoResponse.setFirstName("Julio");
        instructorDtoResponse.setLastName("Magno");
        instructorDtoResponse.setEmail("julio@gmail.com");
        instructorDtoResponse.setAddress("Av. Rendeiras, 1221");


        when(instructorService.getById(1L)).thenReturn(instructorDtoResponse);

        mockMvc.perform(get("/v1/scholarship/instructor/get/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("Julio"))
                .andExpect(jsonPath("$.lastName").value("Magno"))
                .andExpect(jsonPath("$.email").value("julio@gmail.com"))
                .andExpect(jsonPath("$.address").value("Av. Rendeiras, 1221"));
    }

    @Test
    public void testUpdateEndpoint() throws Exception {
        Instructor instructorToUpdate = new Instructor();
        instructorToUpdate.setId(1L);
        instructorToUpdate.setFirstName("Julio");
        instructorToUpdate.setLastName("Magno");
        instructorToUpdate.setEmail("julio@gmail.com");
        instructorToUpdate.setAddress("Av. Rendeiras, 1221");

        when(instructorService.updateInstructor(any(Instructor.class))).thenReturn(instructorToUpdate);

        mockMvc.perform(put("/v1/scholarship/instructor/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Julio"))
                .andExpect(jsonPath("$.lastName").value("Magno"))
                .andExpect(jsonPath("$.email").value("julio@gmail.com"))
                .andExpect(jsonPath("$.address").value("Av. Rendeiras, 1221"));

    }

    @Test
    public void testDeleteEndpoint() throws Exception {
        Long instructorIdToDelete = 1L;

        mockMvc.perform(delete("/v1/scholarship/instructor/delete")
                        .param("id", String.valueOf(instructorIdToDelete)))
                .andExpect(status().isOk())
                .andExpect(content().string("Instructor Deleted!"));

        verify(instructorService, times(1)).deleteInstructor(instructorIdToDelete);
    }
}