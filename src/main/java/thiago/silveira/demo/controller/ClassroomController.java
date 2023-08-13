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
        Classroom classroom = new Classroom();
        classroom.setNumberOfStudents(classroomDtoRequest.getNumberOfStudents());
        classroom.setNumberOfCoordinators(classroomDtoRequest.getNumberOfCoordinators());
        classroom.setNumberOfInstructors(classroomDtoRequest.getNumberOfInstructors());
        classroom.setNumberOfScrumMasters(classroomDtoRequest.getNumberOfScrumMasters());
        classroom.setStatus(classroomDtoRequest.getStatus());
        classroom.setDiscipline(classroomDtoRequest.getDiscipline());
        List<Student> students = new ArrayList<>();

        if (classroom.getNumberOfCoordinators() == 1 && classroom.getNumberOfInstructors() == 3 && classroom.getNumberOfScrumMasters() == 1) {
            classroom.setStatus(Status.STARTED);
        } else {
            classroom.setStatus(Status.WAITING);
        }

        for (StudentDtoRequest student : classroomDtoRequest.getStudents()) {
            Student student1 = new Student();
            student1.setFirstName(student.getFirstName());
            student1.setLastName(student.getLastName());
            student1.setEmail(student.getEmail());
            student1.setAddress(student.getAddress());
            student1.setClassroom(classroom);
            students.add(student1);

        }
        classroom.setStudents(students);

        try {
            classroomService.save(classroom);
            return "Classroom saved!";
        } catch (ClassroomIncorrectFieldException e) {
            return "Classroom not saved";
        }

    }

    @GetMapping("/get/{id}")
    public ClassroomDtoResponse post(@PathVariable Long id) {
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


