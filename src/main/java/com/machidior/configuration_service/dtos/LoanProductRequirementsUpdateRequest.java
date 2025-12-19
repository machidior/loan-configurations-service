package com.machidior.configuration_service.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
@Builder
public class LoanProductRequirementsUpdateRequest {

    @NotNull(message = "Requires guarantor field must not be null.")
    private Boolean requiresGuarantor;
    private Integer minGuarantors;
    @NotNull(message = "Requires collateral field must not be null.")
    private Boolean requiresCollateral;
    private List<String> allowedCollateralTypes;
}
