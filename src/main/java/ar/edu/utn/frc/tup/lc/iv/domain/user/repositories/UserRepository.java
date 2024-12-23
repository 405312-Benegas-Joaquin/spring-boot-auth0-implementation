package ar.edu.utn.frc.tup.lc.iv.domain.user.repositories;

import ar.edu.utn.frc.tup.lc.iv.domain.user.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByAuth0Id(String something);
}
