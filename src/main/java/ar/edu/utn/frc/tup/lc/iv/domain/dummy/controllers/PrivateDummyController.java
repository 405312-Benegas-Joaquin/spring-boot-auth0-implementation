package ar.edu.utn.frc.tup.lc.iv.domain.dummy.controllers;

import ar.edu.utn.frc.tup.lc.iv.domain.dummy.dtos.ResponseDummyDTO;
import ar.edu.utn.frc.tup.lc.iv.domain.dummy.dtos.SaveDummyDTO;
import ar.edu.utn.frc.tup.lc.iv.domain.dummy.services.DummyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dummies")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PrivateDummyController {

    private final DummyService dummyService;

    @GetMapping
    public ResponseEntity<List<ResponseDummyDTO>> getDummyList(@AuthenticationPrincipal Jwt principal) {
        String auth0Id = principal.getSubject();
        return ResponseEntity.ok(dummyService.getDummyList(auth0Id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<List<ResponseDummyDTO>> getAdminDummyList(@AuthenticationPrincipal Jwt principal) {
        String auth0Id = principal.getSubject();
        return ResponseEntity.ok(dummyService.getDummyList(auth0Id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDummyDTO> getDummyById(@PathVariable Long id) {
        return ResponseEntity.ok(dummyService.getDummyById(id));
    }

    @PostMapping
    public ResponseEntity<ResponseDummyDTO> createDummy(@AuthenticationPrincipal Jwt principal, @RequestBody SaveDummyDTO saveDummyDTO) {
        String auth0Id = principal.getSubject();
        return ResponseEntity.ok(dummyService.createDummy(auth0Id, saveDummyDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDummyDTO> updateDummy(@PathVariable Long id, @RequestBody SaveDummyDTO saveDummyDTO) {
        return ResponseEntity.ok(dummyService.updateDummy(id, saveDummyDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteDummyById(@PathVariable Long id) {
        dummyService.deleteDummyById(id);
    }

    public ResponseEntity<List<ResponseDummyDTO>> fallbackForGetAllDummies(Throwable throwable) {
        return ResponseEntity.ok(List.of());
    }
}
  