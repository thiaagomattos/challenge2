package thiago.silveira.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thiago.silveira.demo.dtos.ClassroomDtoRequest;
import thiago.silveira.demo.dtos.ClassroomDtoResponse;
import thiago.silveira.demo.exceptions.ClassroomIncorrectFieldException;
import thiago.silveira.demo.repository.ClassroomRepository;
import thiago.silveira.demo.entity.Classroom;


@Service
public class ClassroomService {
    @Autowired
    ClassroomRepository classroomRepository;

    public Classroom save(Classroom classroomDtoRequest) {


        return classroomRepository.save(classroomDtoRequest);
    }
    public ClassroomDtoResponse getById(Long id){
        Classroom classroom = classroomRepository
                .findById(id)
                .orElseThrow(() -> new ClassroomIncorrectFieldException("Classroom not found!"));
        ClassroomDtoResponse classroomDtoResponse = new ClassroomDtoResponse(classroom.getId(), classroom.getNumberOfStudents(), classroom.getNumberOfCoordinators(),
                classroom.getNumberOfInstructors(), classroom.getNumberOfScrumMasters(), classroom.getStatus(), classroom.getDiscipline());
        return classroomDtoResponse;
    }

    public Classroom updateClassroom(Classroom classroom){
        return classroomRepository.save(classroom);
    }

    public void deleteClassroom(Long id){
        classroomRepository.deleteById(id);
    }
}

