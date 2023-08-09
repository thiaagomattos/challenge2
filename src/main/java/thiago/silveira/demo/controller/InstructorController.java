package thiago.silveira.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import thiago.silveira.demo.dtos.InstructorDtoRequest;
import thiago.silveira.demo.dtos.InstructorDtoResponse;
import thiago.silveira.demo.entity.Instructor;
import thiago.silveira.demo.exceptions.InstructorIncorrectFieldException;
import thiago.silveira.demo.service.InstructorService;

@RestController
@RequestMapping(value = "/scholarship/instructor")
public class InstructorController {

    @Autowired
    InstructorService instructorService;

    @PostMapping("/post")
    public String post(@RequestBody InstructorDtoRequest instructorDtoRequest) {
        try {
            instructorService.save(instructorDtoRequest);
            return "Instructor saved!";
        } catch (InstructorIncorrectFieldException e) {
            return "Instructor not saved";
        }
    }
    @GetMapping("/get/{id}")
    public InstructorDtoResponse post(@PathVariable Long id) {
        return instructorService.getById(id);
    }

    @PutMapping("/update")
    public Instructor updateInstructor(@RequestBody Instructor instructor){
        return instructorService.updateInstructor(instructor);
    }

    @DeleteMapping("/delete")
    public String deleteInstructor(@RequestParam Long id){
        instructorService.deleteInstructor(id);
        return "Instructor Deleted!";
    }
}
