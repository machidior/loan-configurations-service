package com.machidior.configuration_service.dtos.response.requirement;

import com.machidior.configuration_service.enums.RequirementType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GuarantorRequirementResponse {

    private Long id;
    private RequirementType type;
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
