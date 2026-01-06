package com.machidior.configuration_service.dtos;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HousingRequirementRequest {

    private Long productVersionId;
    private String type;
    private Boolean enabled;
    private Boolean mandatory;

    private Boolean ownershipProofRequired;
    private Boolean buildingPlanRequired;
    private Boolean siteInspectionRequired;
    private Boolean valuationRequired;

    private BigDecimal maxLoanToValueRatio;
}
