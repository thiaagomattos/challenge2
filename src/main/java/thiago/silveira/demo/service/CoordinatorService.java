package thiago.silveira.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thiago.silveira.demo.dtos.CoordinatorDtoRequest;
import thiago.silveira.demo.dtos.CoordinatorDtoResponse;
import thiago.silveira.demo.entity.Coordinator;
import thiago.silveira.demo.exceptions.CoordinatorIncorrectFieldException;
import thiago.silveira.demo.repository.CoordinatorRepository;

@Service
public class CoordinatorService {

    @Autowired
    CoordinatorRepository coordinatorRepository;

    public Coordinator save(CoordinatorDtoRequest coordinatorDtoRequest) {

        Coordinator coordinator = new Coordinator(null, coordinatorDtoRequest.getFirstName(), coordinatorDtoRequest.getLastName(), coordinatorDtoRequest.getEmail(),
                coordinatorDtoRequest.getAddress());

        return coordinatorRepository.save(coordinator);
    }

    public CoordinatorDtoResponse getById(Long id){
        Coordinator coordinator = coordinatorRepository
                .findById(id)
                .orElseThrow(() -> new CoordinatorIncorrectFieldException("Coordinator not found!"));
        CoordinatorDtoResponse coordinatorDtoResponse = new CoordinatorDtoResponse(coordinator.getId(), coordinator.getFirstName(), coordinator.getLastName(),
                coordinator.getEmail(), coordinator.getAddress());
        return coordinatorDtoResponse;
    }

    public Coordinator updateCoordinator(Coordinator coordinator){
        return coordinatorRepository.save(coordinator);
    }

    public void deleteCoordinator(Long id){
        coordinatorRepository.deleteById(id);
    }
}