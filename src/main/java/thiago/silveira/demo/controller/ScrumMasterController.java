package thiago.silveira.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import thiago.silveira.demo.dtos.ScrumMasterDtoRequest;
import thiago.silveira.demo.dtos.ScrumMasterDtoResponse;
import thiago.silveira.demo.entity.ScrumMaster;
import thiago.silveira.demo.exceptions.ScrumMasterIncorrectFieldException;
import thiago.silveira.demo.service.ScrumMasterService;

@RestController
@RequestMapping(value = "/scholarship/scrumMaster")
public class ScrumMasterController {

    @Autowired
    ScrumMasterService scrumMasterService;

    @PostMapping("/post")
    public String post(@RequestBody ScrumMasterDtoRequest scrumMasterDtoRequest) {
        try {
            scrumMasterService.save(scrumMasterDtoRequest);
            return "Scrum Master saved!";
        } catch (ScrumMasterIncorrectFieldException e) {
            return "Scrum Master not saved";
        }
    }
    @GetMapping("/get/{id}")
    public ScrumMasterDtoResponse post(@PathVariable Long id) {
        return scrumMasterService.getById(id);
    }

    @PutMapping("/update")
    public ScrumMaster updateScrumMaster(@RequestBody ScrumMaster scrumMaster){
        return scrumMasterService.updateScrumMaster(scrumMaster);
    }

    @DeleteMapping("/delete")
    public String deleteScrumMaster(@RequestParam Long id){
        scrumMasterService.deleteScrumMaster(id);
        return "Scrum Master Deleted!";
    }
}
