package thiago.silveira.demo.dtos;

import jakarta.transaction.Transactional;
import lombok.Data;
import thiago.silveira.demo.entity.Status;

import java.util.ArrayList;
import java.util.List;

@Data
@Transactional
public class ClassroomDtoRequest {

    private Integer numberOfStudents;
    private Integer numberOfCoordinators;
    private Integer numberOfInstructors;
    private Integer numberOfScrumMasters;
    private Status status;
    private String discipline;

    private List<StudentDtoRequest> students = new ArrayList<>();


    public Integer getNumberOfStudents() {
        return getStudents().toArray().length;
    }

    public void setNumberOfStudents(Integer numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    public Integer getNumberOfCoordinators() {
        return numberOfCoordinators;
    }

    public void setNumberOfCoordinators(Integer numberOfCoordinators) {
        this.numberOfCoordinators = numberOfCoordinators;
    }

    public Integer getNumberOfInstructors() {
        return numberOfInstructors;
    }

    public void setNumberOfInstructors(Integer numberOfInstructors) {
        this.numberOfInstructors = numberOfInstructors;
    }

    public Integer getNumberOfScrumMasters() {
        return numberOfScrumMasters;
    }

    public void setNumberOfScrumMasters(Integer numberOfScrumMasters) {
        this.numberOfScrumMasters = numberOfScrumMasters;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }
}