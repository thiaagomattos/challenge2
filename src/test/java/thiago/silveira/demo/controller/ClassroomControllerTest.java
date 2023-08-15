package thiago.silveira.demo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import thiago.silveira.demo.dtos.ClassroomDtoRequest;
import thiago.silveira.demo.entity.Classroom;
import thiago.silveira.demo.exceptions.ClassroomIncorrectFieldException;
import thiago.silveira.demo.service.ClassroomService;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClassroomController.class)
public class ClassroomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ClassroomService classroomService;

    @InjectMocks
    private ClassroomController classroomController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPost_Success() throws Exception {
        ClassroomDtoRequest request = new ClassroomDtoRequest();
        when(classroomService.save(any())).thenReturn(new Classroom());

        mockMvc.perform(post("/v2/scholarship/classroom/post")
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Classroom saved!"));
    }

    @Test
    public void testPost_Failure() throws Exception {
        ClassroomDtoRequest request = new ClassroomDtoRequest();
        when(classroomService.save(any())).thenThrow(new ClassroomIncorrectFieldException("Classroom not found"));

        mockMvc.perform(post("/v2/scholarship/classroom/post")
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Classroom not saved"));
    }

    @Test
    public void testGetById_Success() throws Exception {
        Classroom classroom = new Classroom();
        when(classroomService.getById(anyLong())).thenReturn(classroom);

        mockMvc.perform(get("/v2/scholarship/classroom/get/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    public void testUpdateClassroom_Success() throws Exception {
        Classroom classroom = new Classroom();
        when(classroomService.updateClassroom(any())).thenReturn(classroom);

        mockMvc.perform(put("/v2/scholarship/classroom/update")
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    public void testDeleteClassroom_Success() throws Exception {
        doNothing().when(classroomService).deleteClassroom(anyLong());

        mockMvc.perform(delete("/v2/scholarship/classroom/delete")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Classroom Deleted!"));
    }
}