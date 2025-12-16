package com.machidior.configuration_service.dtos;

import com.machidior.configuration_service.enums.CollateralType;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
@Builder
public class LoanProductRequirementsRequest {
    @NotNull(message = "Requirement has no specific loan product.")
    private Long productId;
    private Boolean requiresGuarantor;
    private Integer minGuarantors;
    private Boolean requiresCollateral;
    private List<CollateralType> allowedCollateralTypes;
}
