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
import thiago.silveira.demo.dtos.CoordinatorDtoRequest;
import thiago.silveira.demo.dtos.CoordinatorDtoResponse;
import thiago.silveira.demo.entity.Coordinator;
import thiago.silveira.demo.service.CoordinatorService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CoordinatorController.class)
public class CoordinatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CoordinatorService coordinatorService;

    private CoordinatorDtoRequest coordinatorDtoRequest;

    @BeforeEach
    public void setUp() {
        coordinatorDtoRequest = new CoordinatorDtoRequest();
        coordinatorDtoRequest.setFirstName("Junior");
        coordinatorDtoRequest.setLastName("Silva");
        coordinatorDtoRequest.setEmail("junior@gmail.com");
        coordinatorDtoRequest.setAddress("Av. Machado, 992");
    }

    @Test
    public void testPostEndpoint() throws Exception {
        CoordinatorDtoRequest request = new CoordinatorDtoRequest();
        when(coordinatorService.save(any())).thenReturn(new Coordinator());

        mockMvc.perform(post("/v1/scholarship/coordinator/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Coordinator saved!"));
    }

    @Test
    public void testGetEndpoint() throws Exception {
        CoordinatorDtoResponse coordinatorDtoResponse = new CoordinatorDtoResponse(1l, "Junior", "Matias", "junior@gmail.com", "Rua das Caldeiras, 552");
        coordinatorDtoResponse.setId(1L);
        coordinatorDtoResponse.setFirstName("Junior");
        coordinatorDtoResponse.setLastName("Silva");
        coordinatorDtoResponse.setEmail("junior@gmail.com");
        coordinatorDtoResponse.setAddress("Av. Machado, 992");


        when(coordinatorService.getById(1L)).thenReturn(coordinatorDtoResponse);

        mockMvc.perform(get("/v1/scholarship/coordinator/get/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("Junior"))
                .andExpect(jsonPath("$.lastName").value("Silva"))
                .andExpect(jsonPath("$.email").value("junior@gmail.com"))
                .andExpect(jsonPath("$.address").value("Av. Machado, 992"));
    }

    @Test
    public void testUpdateEndpoint() throws Exception {
        Coordinator coordinatorToUpdate = new Coordinator();
        coordinatorToUpdate.setId(1L);
        coordinatorToUpdate.setFirstName("Junior");
        coordinatorToUpdate.setLastName("Silva");
        coordinatorToUpdate.setEmail("junior@gmail.com");
        coordinatorToUpdate.setAddress("Rua João Meirelles, 433");

        when(coordinatorService.updateCoordinator(any(Coordinator.class))).thenReturn(coordinatorToUpdate);

        mockMvc.perform(put("/v1/scholarship/coordinator/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Junior"))
                .andExpect(jsonPath("$.lastName").value("Silva"))
                .andExpect(jsonPath("$.email").value("junior@gmail.com"))
                .andExpect(jsonPath("$.address").value("Rua João Meirelles, 433"));

    }

    @Test
    public void testDeleteEndpoint() throws Exception {
        Long coordinatorIdToDelete = 1L;

        mockMvc.perform(delete("/v1/scholarship/coordinator/delete")
                        .param("id", String.valueOf(coordinatorIdToDelete)))
                .andExpect(status().isOk())
                .andExpect(content().string("Coordinator Deleted!"));

        verify(coordinatorService, times(1)).deleteCoordinator(coordinatorIdToDelete);
    }
}