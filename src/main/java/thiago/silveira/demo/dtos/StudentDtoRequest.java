package thiago.silveira.demo.dtos;

import lombok.Data;
import thiago.silveira.demo.entity.Classroom;
import thiago.silveira.demo.service.ClassroomService;


@Data
public class StudentDtoRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private Long classroom_id;

    public Long getClassroom() {
        return this.classroom_id;
    }
    public void setClassroom(Long id) {
        this.classroom_id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
