package com.machidior.configuration_service.dtos;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GuaranteeRequirementRequest {

    private Long productVersionId;
    private String type;
    private Boolean enabled;
    private Boolean mandatory;

    private Integer minGuarantors;
    private Integer maxGuarantors;

    private Boolean guarantorIncomeProofRequired;
    private Boolean guarantorEmploymentRequired;
    private Boolean guarantorRelationRequired;

    private BigDecimal minGuarantorIncome;
}
