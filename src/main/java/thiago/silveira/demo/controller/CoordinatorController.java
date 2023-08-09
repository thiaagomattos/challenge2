package thiago.silveira.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import thiago.silveira.demo.dtos.CoordinatorDtoRequest;
import thiago.silveira.demo.dtos.CoordinatorDtoResponse;
import thiago.silveira.demo.entity.Coordinator;
import thiago.silveira.demo.exceptions.CoordinatorIncorrectFieldException;
import thiago.silveira.demo.service.CoordinatorService;

@RestController
@RequestMapping(value = "/scholarship/coordinator")
public class CoordinatorController {

    @Autowired
    CoordinatorService coordinatorService;

    @PostMapping("/post")
    public String post(@RequestBody CoordinatorDtoRequest coordinatorDtoRequest) {
        try {
            coordinatorService.save(coordinatorDtoRequest);
            return "Coordinator saved!";
        } catch (CoordinatorIncorrectFieldException e) {
            return "Coordinator not saved";
        }
    }
    @GetMapping("/get/{id}")
    public CoordinatorDtoResponse post(@PathVariable Long id) {
        return coordinatorService.getById(id);
    }

    @PutMapping("/update")
    public Coordinator updateCoordinator(@RequestBody Coordinator coordinator){
        return coordinatorService.updateCoordinator(coordinator);
    }

    @DeleteMapping("/delete")
    public String deleteCoordinator(@RequestParam Long id){
        coordinatorService.deleteCoordinator(id);
        return "Coordinator Deleted!";
    }
}
