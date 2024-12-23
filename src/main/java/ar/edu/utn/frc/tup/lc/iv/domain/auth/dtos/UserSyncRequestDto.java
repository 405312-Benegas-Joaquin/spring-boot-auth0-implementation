package ar.edu.utn.frc.tup.lc.iv.domain.auth.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSyncRequestDto {
    private String email;
    private String name;
}
