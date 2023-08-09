package thiago.silveira.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thiago.silveira.demo.dtos.ScrumMasterDtoRequest;
import thiago.silveira.demo.dtos.ScrumMasterDtoResponse;
import thiago.silveira.demo.entity.ScrumMaster;
import thiago.silveira.demo.exceptions.ScrumMasterIncorrectFieldException;
import thiago.silveira.demo.repository.ScrumMasterRepository;

@Service
public class ScrumMasterService {

    @Autowired
    ScrumMasterRepository scrumMasterRepository;

    public ScrumMaster save(ScrumMasterDtoRequest scrumMasterDtoRequest) {

        ScrumMaster scrumMaster = new ScrumMaster(null, scrumMasterDtoRequest.getFirstName(), scrumMasterDtoRequest.getLastName(), scrumMasterDtoRequest.getEmail(),
                scrumMasterDtoRequest.getAddress());

        return scrumMasterRepository.save(scrumMaster);
    }

    public ScrumMasterDtoResponse getById(Long id){
        ScrumMaster scrumMaster = scrumMasterRepository
                .findById(id)
                .orElseThrow(() -> new ScrumMasterIncorrectFieldException("ScrumMaster not found!"));
        ScrumMasterDtoResponse scrumMasterDtoResponse = new ScrumMasterDtoResponse(scrumMaster.getId(), scrumMaster.getFirstName(), scrumMaster.getLastName(),
                scrumMaster.getEmail(), scrumMaster.getAddress());
        return scrumMasterDtoResponse;
    }

    public ScrumMaster updateScrumMaster(ScrumMaster ScrumMaster){
        return scrumMasterRepository.save(ScrumMaster);
    }

    public void deleteScrumMaster(Long id){
        scrumMasterRepository.deleteById(id);
    }
}
