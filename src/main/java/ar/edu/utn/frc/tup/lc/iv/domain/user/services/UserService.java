package ar.edu.utn.frc.tup.lc.iv.domain.user.services;

import ar.edu.utn.frc.tup.lc.iv.domain.user.entities.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserEntity findOrCreateUser(String auth0Id, String email, String name);
}
