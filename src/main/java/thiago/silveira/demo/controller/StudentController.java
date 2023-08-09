package thiago.silveira.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import thiago.silveira.demo.dtos.StudentDtoRequest;
import thiago.silveira.demo.dtos.StudentDtoResponse;
import thiago.silveira.demo.entity.Student;
import thiago.silveira.demo.exceptions.StudentIncorrectFieldException;
import thiago.silveira.demo.service.StudentService;

@RestController
@RequestMapping(value = "/scholarship/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/post")
    public String post(@RequestBody StudentDtoRequest studentDtoRequest) {
        try {
            studentService.save(studentDtoRequest);
            return "Student saved!";
        } catch (StudentIncorrectFieldException e) {
            return "Student not saved";
        }
    }
    @GetMapping("/get/{id}")
    public StudentDtoResponse post(@PathVariable Long id) {
        return studentService.getById(id);
    }

    @PutMapping("/update")
    public Student updateStudent(@RequestBody Student student){
        return studentService.updateStudent(student);
    }

    @DeleteMapping("/delete")
    public String deleteStudent(@RequestParam Long id){
        studentService.deleteStudent(id);
        return "Student Deleted!";
    }
}
