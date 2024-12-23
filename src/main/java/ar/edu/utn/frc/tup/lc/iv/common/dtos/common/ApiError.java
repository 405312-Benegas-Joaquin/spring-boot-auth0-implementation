package ar.edu.utn.frc.tup.lc.iv.common.dtos.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiError {
    private String timestamp;
    private Integer status;
    private String error;
    private String message;
}
