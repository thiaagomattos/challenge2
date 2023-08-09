package thiago.silveira.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thiago.silveira.demo.entity.Coordinator;

@Repository
public interface CoordinatorRepository extends JpaRepository<Coordinator, Long> {
}
