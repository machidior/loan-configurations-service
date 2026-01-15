package com.machidior.configuration_service.dtos.request.requirement;


import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CollateralRequirementRequest {

    private Long productVersionId;
    private Boolean enabled;
    private Boolean mandatory;

    private List<String> allowedTypes;

    private Integer minCount;
    private Integer maxCount;

    private Boolean descriptionRequired;
    private Boolean ownershipProofRequired;
    private Boolean insuranceRequired;
    private Boolean valuationRequired;
    private Boolean photoRequired;

    private BigDecimal minLoanAmountToValueRatio;
}
