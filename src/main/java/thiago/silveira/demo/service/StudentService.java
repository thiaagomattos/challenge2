package thiago.silveira.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thiago.silveira.demo.dtos.StudentDtoRequest;
import thiago.silveira.demo.entity.Student;
import thiago.silveira.demo.exceptions.StudentIncorrectFieldException;
import thiago.silveira.demo.repository.StudentRepository;

    @Service
    public class StudentService {

        @Autowired
        StudentRepository studentRepository;

        public Student save(StudentDtoRequest studentDtoRequest) {

            Student student = new Student(null, studentDtoRequest.getFirstName(), studentDtoRequest.getLastName(), studentDtoRequest.getEmail(),
                studentDtoRequest.getAddress());

        return studentRepository.save(student);
    }

    public StudentDtoRequest getById(Long id){
        Student student = studentRepository
                .findById(id)
                .orElseThrow(() -> new StudentIncorrectFieldException("Student not found!"));
        StudentDtoRequest studentDtoRequest = new StudentDtoRequest(student.getId(), student.getFirstName(), student.getLastName(),
                student.getEmail(), student.getAddress());
        return studentDtoRequest;
    }
}
