package ar.edu.utn.frc.tup.lc.iv.domain.dummy.clients;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DummyDto(
        Long id,
        String dummy
) {
}
