package thiago.silveira.demo.dtos;

public class SquadDtoResponse {

    private Long id;
    private Integer numberOfStudents;
    private String nameSquad;

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

    public SquadDtoResponse(Long id, Integer numberOfStudents, String nameSquad) {
        this.id = id;
        this.numberOfStudents = numberOfStudents;
        this.nameSquad = nameSquad;
    }
}
