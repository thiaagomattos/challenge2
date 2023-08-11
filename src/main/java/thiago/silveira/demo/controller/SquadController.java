package thiago.silveira.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import thiago.silveira.demo.dtos.SquadDtoRequest;
import thiago.silveira.demo.dtos.SquadDtoResponse;
import thiago.silveira.demo.entity.Squad;
import thiago.silveira.demo.exceptions.SquadIncorrectFieldException;
import thiago.silveira.demo.service.SquadService;

@RestController
@RequestMapping(value = "/v2/scholarship/squad")
public class SquadController {

    @Autowired
    SquadService squadService;

    @PostMapping("/post")
    public String post(@RequestBody SquadDtoRequest squadDtoRequest) {
        try {
            squadService.save(squadDtoRequest);
            return "Squad saved!";
        } catch (SquadIncorrectFieldException e) {
            return "Squad not saved";
        }
    }

    @GetMapping("/get/{id}")
    public SquadDtoResponse post(@PathVariable Long id) {
        return squadService.getById(id);
    }

    @PutMapping("/update")
    public Squad updateSquad(@RequestBody Squad squad) {
        return squadService.updateSquad(squad);
    }

    @DeleteMapping("/delete")
    public String deleteSquad(@RequestParam Long id) {
        squadService.deleteSquad(id);
        return "Squad deleted!";
    }
}
