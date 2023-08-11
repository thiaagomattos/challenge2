package thiago.silveira.demo.dtos;

public class SquadDtoRequest {

    private Integer numberOfStudents;
    private String nameSquad;

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
}
