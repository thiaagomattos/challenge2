package thiago.silveira.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import thiago.silveira.demo.dtos.TeamDtoRequest;
import thiago.silveira.demo.dtos.TeamDtoResponse;
import thiago.silveira.demo.entity.Team;
import thiago.silveira.demo.exceptions.TeamIncorrectFieldException;
import thiago.silveira.demo.service.TeamService;

@RestController
@RequestMapping(value = "/scholarship/class")
public class TeamController {
    @Autowired
    TeamService teamService;

    @PostMapping("/post")
    public String post(@RequestBody TeamDtoRequest teamDtoRequest) {
        try {
            teamService.save(teamDtoRequest);
            return "Team saved!";
        } catch (TeamIncorrectFieldException e) {
            return "Team not saved";
        }
    }

    @GetMapping("/get/{id}")
    public TeamDtoResponse post(@PathVariable Long id) {
        return teamService.getById(id);
    }

    @PutMapping("/update")
    public Team updateTeam(@RequestBody Team team){
        return teamService.updateTeam(team);
    }

    @DeleteMapping("/delete")
    public String deleteTeam(@RequestParam Long id){
        teamService.deleteTeam(id);
        return "Team Deleted!";
    }
}

