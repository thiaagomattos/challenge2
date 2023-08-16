package thiago.silveira.demo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import thiago.silveira.demo.dtos.ClassroomDtoRequest;
import thiago.silveira.demo.entity.Classroom;
import thiago.silveira.demo.entity.Status;
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
    private ClassroomDtoRequest classroomDtoRequest;

    @BeforeEach
    public void setUp() {
        classroomDtoRequest = new ClassroomDtoRequest();
        classroomDtoRequest.setNumberOfStudents(15);
        classroomDtoRequest.setNumberOfCoordinators(1);
        classroomDtoRequest.setNumberOfInstructors(3);
        classroomDtoRequest.setNumberOfScrumMasters(1);
        classroomDtoRequest.setStatus(Status.STARTED);
        classroomDtoRequest.setDiscipline("English");
    }

    @Test
    public void testPostEndpoint() throws Exception {
        ClassroomDtoRequest request = new ClassroomDtoRequest();
        when(classroomService.save(any())).thenReturn(new Classroom());

        mockMvc.perform(post("/v2/scholarship/classroom/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Classroom saved!"));
    }

    @Test
    public void testGetEndpoint() throws Exception {
        Classroom classroom = new Classroom(1L, 15, 1, 3, 1, Status.STARTED, "English");
        classroom.setId(1L);
        classroom.setNumberOfStudents(15);
        classroom.setNumberOfCoordinators(1);
        classroom.setNumberOfInstructors(3);
        classroom.setNumberOfScrumMasters(1);
        classroom.setStatus(Status.STARTED);
        classroom.setDiscipline("English");

        when(classroomService.getById(1L)).thenReturn(classroom);

        mockMvc.perform(get("/v2/scholarship/classroom/get/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.numberOfStudents").value(15))
                .andExpect(jsonPath("$.numberOfCoordinators").value(1))
                .andExpect(jsonPath("$.numberOfInstructors").value(3))
                .andExpect(jsonPath("$.numberOfScrumMasters").value(1))
                .andExpect(jsonPath("$.status").value(Status.STARTED))
                .andExpect(jsonPath("$.discipline").value("English"));
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