package ar.edu.utn.frc.tup.lc.iv.domain.auth.controllers;

import ar.edu.utn.frc.tup.lc.iv.domain.auth.dtos.UserSyncRequestDto;
import ar.edu.utn.frc.tup.lc.iv.domain.user.entities.UserEntity;
import ar.edu.utn.frc.tup.lc.iv.domain.user.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/sync-user")
    public ResponseEntity<UserEntity> syncUser(@AuthenticationPrincipal Jwt principal, @RequestBody UserSyncRequestDto userDto) {
        String auth0Id = principal.getSubject();
        return ResponseEntity.ok(userService.findOrCreateUser(auth0Id, userDto.getEmail(), userDto.getName()));
    }
}
