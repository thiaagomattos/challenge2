package thiago.silveira.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thiago.silveira.demo.dtos.ClassroomDtoRequest;
import thiago.silveira.demo.dtos.StudentDtoRequest;
import thiago.silveira.demo.entity.Status;
import thiago.silveira.demo.entity.Student;
import thiago.silveira.demo.exceptions.ClassroomIncorrectFieldException;
import thiago.silveira.demo.repository.ClassroomRepository;
import thiago.silveira.demo.entity.Classroom;

import java.util.ArrayList;
import java.util.List;


@Service
public class ClassroomService {
    @Autowired
    ClassroomRepository classroomRepository;

    public Classroom save(ClassroomDtoRequest classroomDtoRequest) {

        Classroom classroom = new Classroom();
        classroom.setNumberOfStudents(classroomDtoRequest.getNumberOfStudents());
        classroom.setNumberOfCoordinators(classroomDtoRequest.getNumberOfCoordinators());
        classroom.setNumberOfInstructors(classroomDtoRequest.getNumberOfInstructors());
        classroom.setNumberOfScrumMasters(classroomDtoRequest.getNumberOfScrumMasters());
        classroom.setStatus(classroomDtoRequest.getStatus());
        classroom.setDiscipline(classroomDtoRequest.getDiscipline());
        List<Student> students = new ArrayList<>();

        if (classroom.getNumberOfCoordinators() == 1 &&
                classroom.getNumberOfInstructors() == 3 &&
                classroom.getNumberOfScrumMasters() == 1 &&
                (classroom.getNumberOfStudents() > 15 && classroom.getNumberOfStudents() < 30))
        {
            classroom.setStatus(Status.STARTED);
        } else {
            classroom.setStatus(Status.WAITING);
        }


        for (StudentDtoRequest student : classroomDtoRequest.getStudents()) {
            Student student1 = new Student();
            student1.setFirstName(student.getFirstName());
            student1.setLastName(student.getLastName());
            student1.setEmail(student.getEmail());
            student1.setAddress(student.getAddress());
            student1.setClassroom(classroom);
            students.add(student1);

        }
        classroom.setStudents(students);

        return classroomRepository.save(classroom);
    }

    public Classroom getById(Long id) {
        Classroom classroom = classroomRepository
                .findById(id)
                .orElseThrow(() -> new ClassroomIncorrectFieldException("Classroom not found!"));
        return classroom;
    }

    public Classroom updateClassroom(Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    public void deleteClassroom(Long id) {
        classroomRepository.deleteById(id);
    }

}

