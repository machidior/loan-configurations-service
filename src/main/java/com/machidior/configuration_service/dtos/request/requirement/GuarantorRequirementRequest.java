package com.machidior.configuration_service.dtos.request.requirement;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GuarantorRequirementRequest {

    private Long productVersionId;
    private String type;
    private Boolean enabled;
    private Boolean mandatory;

    private Integer minGuarantors;
    private Integer maxGuarantors;

    private Boolean guarantorIncomeProofRequired;
    private Boolean guarantorEmploymentRequired;
    private Boolean guarantorRelationRequired;

    private Boolean passportPhotoRequired;
    private Boolean idDocumentRequired;
    private Boolean guarantorConsentRequired;

    private BigDecimal minGuarantorIncome;
}
