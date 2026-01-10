package com.machidior.configuration_service.dtos.response.requirement;

import com.machidior.configuration_service.enums.RequirementType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HousingRequirementResponse{

    private Long id;
    private RequirementType type;
    private Boolean enabled;
    private Boolean mandatory;

    private Boolean ownershipProofRequired;
    private Boolean buildingPlanRequired;
    private Boolean siteInspectionRequired;
    private Boolean valuationRequired;

    private BigDecimal maxLoanToValueRatio;
}
