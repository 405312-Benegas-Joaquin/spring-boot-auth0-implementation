package ar.edu.utn.frc.tup.lc.iv.domain.dummy.repositories.jpa;

import ar.edu.utn.frc.tup.lc.iv.domain.dummy.entities.DummyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DummyJpaRepository extends JpaRepository<DummyEntity, Long> {
    Optional<DummyEntity> findByDummy(String something);

    Optional<List<DummyEntity>> findByUser_Auth0Id(String auth0Id);
}
  