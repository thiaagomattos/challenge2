package thiago.silveira.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thiago.silveira.demo.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
