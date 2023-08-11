package thiago.silveira.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thiago.silveira.demo.dtos.SquadDtoRequest;
import thiago.silveira.demo.dtos.SquadDtoResponse;
import thiago.silveira.demo.entity.Squad;
import thiago.silveira.demo.exceptions.SquadIncorrectFieldException;
import thiago.silveira.demo.repository.SquadRepository;

@Service
public class SquadService {

    @Autowired
    SquadRepository squadRepository;

        public Squad save(SquadDtoRequest squadDtoRequest){

            Squad squad = new Squad(null, squadDtoRequest.getNumberOfStudents(), squadDtoRequest.getNameSquad());

            return squadRepository.save(squad);
        }

        public SquadDtoResponse getById(Long id){
            Squad squad = squadRepository
                    .findById(id)
                    .orElseThrow(() -> new SquadIncorrectFieldException("Squad not found!"));
            SquadDtoResponse squadDtoResponse = new SquadDtoResponse(squad.getId(), squad.getNumberOfStudents(), squad.getNameSquad());
            return squadDtoResponse;
        }

    public Squad updateSquad(Squad squad){
        return squadRepository.save(squad);
    }

    public void deleteSquad(Long id){
        squadRepository.deleteById(id);
    }
}
