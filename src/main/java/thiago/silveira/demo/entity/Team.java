package thiago.silveira.demo.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;


@Entity
@Table(name = "TEAM")
@Data
@Transactional
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numberOfStudents;
    private Integer numberOfCoordinators;
    private Integer numberOfInstructors;
    private Integer numberOfScrumMasters;
    private String status;
    private String discipline;

    public Team() {
    }

    public Team(Long id, Integer numberOfStudents, Integer numberOfCoordinators, Integer numberOfInstructors, Integer numberOfScrumMasters, String status, String discipline) {
        this.id = id;
        this.numberOfStudents = numberOfStudents;
        this.numberOfCoordinators = numberOfCoordinators;
        this.numberOfInstructors = numberOfInstructors;
        this.numberOfScrumMasters = numberOfScrumMasters;
        this.status = status;
        this.discipline = discipline;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", numberOfStudents=" + numberOfStudents +
                ", numberOfCoordinators=" + numberOfCoordinators +
                ", numberOfInstructors=" + numberOfInstructors +
                ", numberOfScrumMasters=" + numberOfScrumMasters +
                ", status='" + status + '\'' +
                ", discipline='" + discipline + '\'' +
                '}';
    }
}


