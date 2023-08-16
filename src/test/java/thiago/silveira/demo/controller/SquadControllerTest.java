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
import thiago.silveira.demo.dtos.SquadDtoRequest;
import thiago.silveira.demo.dtos.SquadDtoResponse;
import thiago.silveira.demo.entity.Squad;
import thiago.silveira.demo.service.SquadService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SquadController.class)
public class SquadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SquadService squadService;

    private SquadDtoRequest squadDtoRequest;

    @BeforeEach
    public void setUp() {
        squadDtoRequest = new SquadDtoRequest();
        squadDtoRequest.setNumberOfStudents(6);
        squadDtoRequest.setNameSquad("Kambambans");
    }

    @Test
    public void testPostEndpoint() throws Exception {
        SquadDtoRequest request = new SquadDtoRequest();
        when(squadService.save(any())).thenReturn(new Squad());

        mockMvc.perform(post("/v2/scholarship/squad/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Squad saved!"));
    }

    @Test
    public void testGetEndpoint() throws Exception {
        SquadDtoResponse squadDtoResponse = new SquadDtoResponse(1L, 6, "Kambambans");
        squadDtoResponse.setId(1L);
        squadDtoResponse.setNumberOfStudents(6);
        squadDtoResponse.setNameSquad("Kambambans");

        when(squadService.getById(1L)).thenReturn(squadDtoResponse);

        mockMvc.perform(get("/v2/scholarship/squad/get/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.numberOfStudents").value(6))
                .andExpect(jsonPath("$.nameSquad").value("Kambambans"));
    }

    @Test
    public void testUpdateEndpoint() throws Exception {
        Squad squadToUpdate = new Squad();
        squadToUpdate.setId(1L);
        squadToUpdate.setNumberOfStudents(6);
        squadToUpdate.setNameSquad("Kambambans");


        when(squadService.updateSquad(any(Squad.class))).thenReturn(squadToUpdate);

        mockMvc.perform(put("/v2/scholarship/squad/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.numberOfStudents").value(6))
                .andExpect(jsonPath("$.nameSquad").value("Kambambans"));
    }

    @Test
    public void testDeleteEndpoint() throws Exception {
        Long squadIdToDelete = 1L;

        mockMvc.perform(delete("/v2/scholarship/squad/delete")
                        .param("id", String.valueOf(squadIdToDelete)))
                .andExpect(status().isOk())
                .andExpect(content().string("Squad deleted!"));

        verify(squadService, times(1)).deleteSquad(squadIdToDelete);
    }
}