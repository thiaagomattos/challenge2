package thiago.silveira.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import thiago.silveira.demo.dtos.StudentDtoRequest;
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
}
