package thiago.silveira.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thiago.silveira.demo.dtos.InstructorDtoRequest;
import thiago.silveira.demo.dtos.InstructorDtoResponse;
import thiago.silveira.demo.entity.Instructor;
import thiago.silveira.demo.exceptions.InstructorIncorrectFieldException;
import thiago.silveira.demo.repository.InstructorRepository;

@Service
public class InstructorService {

    @Autowired
    InstructorRepository instructorRepository;

    public Instructor save(InstructorDtoRequest instructorDtoRequest) {

        Instructor instructor = new Instructor(null, instructorDtoRequest.getFirstName(), instructorDtoRequest.getLastName(), instructorDtoRequest.getEmail(),
                instructorDtoRequest.getAddress());

        return instructorRepository.save(instructor);
    }

    public InstructorDtoResponse getById(Long id){
        Instructor instructor = instructorRepository
                .findById(id)
                .orElseThrow(() -> new InstructorIncorrectFieldException("instructor not found!"));
        InstructorDtoResponse instructorDtoResponse = new InstructorDtoResponse(instructor.getId(), instructor.getFirstName(), instructor.getLastName(),
                instructor.getEmail(), instructor.getAddress());
        return instructorDtoResponse;
    }

    public Instructor updateInstructor(Instructor instructor){
        return instructorRepository.save(instructor);
    }

    public void deleteInstructor(Long id){
        instructorRepository.deleteById(id);
    }
}
