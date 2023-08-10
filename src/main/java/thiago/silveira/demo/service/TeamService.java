package thiago.silveira.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thiago.silveira.demo.dtos.TeamDtoRequest;
import thiago.silveira.demo.dtos.TeamDtoResponse;
import thiago.silveira.demo.exceptions.TeamIncorrectFieldException;
import thiago.silveira.demo.repository.TeamRepository;
import thiago.silveira.demo.entity.Team;


@Service
public class TeamService {
    @Autowired
    TeamRepository teamRepository;

    public Team save(TeamDtoRequest teamDtoRequest) {

        Team team = new Team(null, teamDtoRequest.getNumberOfStudents(), teamDtoRequest.getNumberOfCoordinators(), teamDtoRequest.getNumberOfInstructors(),
                teamDtoRequest.getNumberOfScrumMasters(), teamDtoRequest.getStatus(), teamDtoRequest.getDiscipline());

        return teamRepository.save(team);
    }
    public TeamDtoResponse getById(Long id){
        Team team = teamRepository
                .findById(id)
                .orElseThrow(() -> new TeamIncorrectFieldException("Team not found!"));
        TeamDtoResponse teamDtoResponse = new TeamDtoResponse(team.getId(), team.getNumberOfStudents(), team.getNumberOfCoordinators(),
                team.getNumberOfInstructors(), team.getNumberOfScrumMasters(), team.getStatus(), team.getDiscipline());
        return teamDtoResponse;
    }

    public Team updateTeam(Team team){
        return teamRepository.save(team);
    }

    public void deleteTeam(Long id){
        teamRepository.deleteById(id);
    }
}

