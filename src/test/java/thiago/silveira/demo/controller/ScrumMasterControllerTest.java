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
import thiago.silveira.demo.dtos.ScrumMasterDtoRequest;
import thiago.silveira.demo.dtos.ScrumMasterDtoResponse;
import thiago.silveira.demo.entity.ScrumMaster;
import thiago.silveira.demo.service.ScrumMasterService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ScrumMasterController.class)
public class ScrumMasterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScrumMasterService scrumMasterService;

    private ScrumMasterDtoRequest scrumMasterDtoRequest;

    @BeforeEach
    public void setUp() {
        scrumMasterDtoRequest = new ScrumMasterDtoRequest();
        scrumMasterDtoRequest.setFirstName("Marcos");
        scrumMasterDtoRequest.setLastName("Pedroso");
        scrumMasterDtoRequest.setEmail("marcos@gmail.com");
        scrumMasterDtoRequest.setAddress("Av. Machado, 992");
    }

    @Test
    public void testPostEndpoint() throws Exception {
        ScrumMasterDtoRequest request = new ScrumMasterDtoRequest();
        when(scrumMasterService.save(any())).thenReturn(new ScrumMaster());

        mockMvc.perform(post("/v1/scholarship/scrumMaster/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Scrum Master saved!"));
    }

    @Test
    public void testGetEndpoint() throws Exception {
        ScrumMasterDtoResponse scrumMasterDtoResponse = new ScrumMasterDtoResponse(1L, "Marcos", "Pedroso", "marcos@gmail.com", "Rua das Gaivotas, 521");
        scrumMasterDtoResponse.setId(1L);
        scrumMasterDtoResponse.setFirstName("Marcos");
        scrumMasterDtoResponse.setLastName("Pedroso");
        scrumMasterDtoResponse.setEmail("marcos@gmail.com");
        scrumMasterDtoResponse.setAddress("Rua das Gaivotas, 521");


        when(scrumMasterService.getById(1L)).thenReturn(scrumMasterDtoResponse);

        mockMvc.perform(get("/v1/scholarship/scrumMaster/get/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("Marcos"))
                .andExpect(jsonPath("$.lastName").value("Pedroso"))
                .andExpect(jsonPath("$.email").value("marcos@gmail.com"))
                .andExpect(jsonPath("$.address").value("Rua das Gaivotas, 521"));
    }

    @Test
    public void testUpdateEndpoint() throws Exception {
        ScrumMaster scrumMasterToUpdate = new ScrumMaster();
        scrumMasterToUpdate.setId(1L);
        scrumMasterToUpdate.setFirstName("Marcos");
        scrumMasterToUpdate.setLastName("Pedroso");
        scrumMasterToUpdate.setEmail("marcos@gmail.com");
        scrumMasterToUpdate.setAddress("Rua das Gaivotas, 521");

        when(scrumMasterService.updateScrumMaster(any(ScrumMaster.class))).thenReturn(scrumMasterToUpdate);

        mockMvc.perform(put("/v1/scholarship/scrumMaster/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Marcos"))
                .andExpect(jsonPath("$.lastName").value("Pedroso"))
                .andExpect(jsonPath("$.email").value("marcos@gmail.com"))
                .andExpect(jsonPath("$.address").value("Rua das Gaivotas, 521"));

    }

    @Test
    public void testDeleteEndpoint() throws Exception {
        Long scrumMasterIdToDelete = 1L;

        mockMvc.perform(delete("/v1/scholarship/scrumMaster/delete")
                        .param("id", String.valueOf(scrumMasterIdToDelete)))
                .andExpect(status().isOk())
                .andExpect(content().string("Scrum Master Deleted!"));

        verify(scrumMasterService, times(1)).deleteScrumMaster(scrumMasterIdToDelete);
    }
}