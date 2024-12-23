package ar.edu.utn.frc.tup.lc.iv.domain.user.services.implementation;

import ar.edu.utn.frc.tup.lc.iv.domain.user.entities.UserEntity;
import ar.edu.utn.frc.tup.lc.iv.domain.user.repositories.UserRepository;
import ar.edu.utn.frc.tup.lc.iv.domain.user.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserEntity findOrCreateUser(String auth0Id, String email, String name) {
        // Validación de entradas
        if (auth0Id == null || auth0Id.isBlank()) {
            throw new IllegalArgumentException("El auth0Id no puede ser nulo o vacío.");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("El email no puede ser nulo o vacío.");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        }

        Optional<UserEntity> userEntityOptional = userRepository.findByAuth0Id(auth0Id);

        if (userEntityOptional.isPresent()) {
            return userEntityOptional.get();
        } else {
            return createUser(auth0Id, email, name);
        }

    }

    /**
     * Método privado para encapsular la creación de un nuevo usuario.
     */
    private UserEntity createUser(String auth0Id, String email, String name) {
        UserEntity newUser = new UserEntity();
        newUser.setAuth0Id(auth0Id);
        newUser.setEmail(email);
        newUser.setName(name);

        return userRepository.save(newUser);
    }
}
