package thiago.silveira.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thiago.silveira.demo.entity.ScrumMaster;

@Repository
public interface ScrumMasterRepository extends JpaRepository<ScrumMaster, Long> {
}
