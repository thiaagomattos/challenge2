package thiago.silveira.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thiago.silveira.demo.entity.Squad;

@Repository
public interface SquadRepository extends JpaRepository<Squad, Long> {
}

