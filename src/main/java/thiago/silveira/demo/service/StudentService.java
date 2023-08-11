package thiago.silveira.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thiago.silveira.demo.dtos.StudentDtoRequest;
import thiago.silveira.demo.dtos.StudentDtoResponse;
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

    public StudentDtoResponse getById(Long id){
        Student student = studentRepository
                .findById(id)
                .orElseThrow(() -> new StudentIncorrectFieldException("Student not found!"));
        StudentDtoResponse studentDtoResponse = new StudentDtoResponse(student.getId(), student.getFirstName(), student.getLastName(),
                student.getEmail(), student.getAddress());
        return studentDtoResponse;
    }

    public Student updateStudent(Student student){
            return studentRepository.save(student);
    }

    public void deleteStudent(Long id){
            studentRepository.deleteById(id);
    }
}
