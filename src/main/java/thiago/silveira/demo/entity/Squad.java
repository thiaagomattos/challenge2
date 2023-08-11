package thiago.silveira.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "SQUAD")
public class Squad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numberOfStudents;
    private String nameSquad;

    public Squad() {
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

    public String getNameSquad() {
        return nameSquad;
    }

    public void setNameSquad(String nameSquad) {
        this.nameSquad = nameSquad;
    }

    @Override
    public String toString() {
        return "Squad{" +
                "id=" + id +
                ", numberOfStudents=" + numberOfStudents +
                ", nameSquad='" + nameSquad + '\'' +
                '}';
    }

    public Squad(Long id, Integer numberOfStudents, String nameSquad) {
        this.id = id;
        this.numberOfStudents = numberOfStudents;
        this.nameSquad = nameSquad;
    }
}
