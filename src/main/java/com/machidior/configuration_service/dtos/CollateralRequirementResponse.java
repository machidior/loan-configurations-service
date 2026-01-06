package com.machidior.configuration_service.dtos;

import com.machidior.configuration_service.enums.CollateralType;
import com.machidior.configuration_service.enums.RequirementType;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CollateralRequirementResponse {

    private Long id;
    private RequirementType type;
    private Boolean enabled;
    private Boolean mandatory;

    private List<CollateralType> allowedTypes;

    private Integer minCount;
    private Integer maxCount;

    private Boolean descriptionRequired;
    private Boolean ownershipProofRequired;
    private Boolean insuranceRequired;
    private Boolean valuationRequired;
    private Boolean photoRequired;

    private BigDecimal minAverageMonthlyTurnOver;
}
