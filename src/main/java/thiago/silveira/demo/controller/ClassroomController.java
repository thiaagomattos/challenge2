package thiago.silveira.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import thiago.silveira.demo.dtos.ClassroomDtoRequest;
import thiago.silveira.demo.dtos.ClassroomDtoResponse;
import thiago.silveira.demo.dtos.StudentDtoRequest;
import thiago.silveira.demo.entity.Classroom;
import thiago.silveira.demo.entity.Status;
import thiago.silveira.demo.entity.Student;
import thiago.silveira.demo.exceptions.ClassroomIncorrectFieldException;
import thiago.silveira.demo.exceptions.StudentIncorrectFieldException;
import thiago.silveira.demo.service.ClassroomService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "v2/scholarship/classroom")
public class ClassroomController {
    @Autowired
    ClassroomService classroomService;

    @PostMapping("/post")
    public String post(@RequestBody ClassroomDtoRequest classroomDtoRequest) {
        try {
            classroomService.save(classroomDtoRequest);
            return "Classroom saved!";
        } catch (ClassroomIncorrectFieldException e) {
            return "Classroom not saved";
        }
    }

    @GetMapping("/get/{id}")
    public Classroom post(@PathVariable Long id) {
        return classroomService.getById(id);
    }

    @PutMapping("/update")
    public Classroom updateClassroom(@RequestBody Classroom classroom){
        return classroomService.updateClassroom(classroom);
    }

    @DeleteMapping("/delete")
    public String deleteClassroom(@RequestParam Long id){
        classroomService.deleteClassroom(id);
        return "Classroom Deleted!";
    }


}


