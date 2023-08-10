package thiago.silveira.demo.dtos;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.transaction.Transactional;
import lombok.Data;
import thiago.silveira.demo.entity.Student;

@Data
@Transactional
public class TeamDtoRequest {

    private Integer numberOfStudents;
    private Integer numberOfCoordinators;
    private Integer numberOfInstructors;
    private Integer numberOfScrumMasters;
    private String status;
    private String discipline;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "fk_student_id")
//    private Student student;

    public Integer getNumberOfStudents() {
        return numberOfStudents;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }
}