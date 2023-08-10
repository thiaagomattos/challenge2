package thiago.silveira.demo.dtos;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.transaction.Transactional;
import lombok.Data;
import thiago.silveira.demo.entity.Team;

@Data
@Transactional
public class StudentDtoRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String address;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "fk_team_id")
//    private Team team;

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
