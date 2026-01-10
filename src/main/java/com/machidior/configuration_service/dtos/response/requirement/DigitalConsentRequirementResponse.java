package com.machidior.configuration_service.dtos.response.requirement;

import com.machidior.configuration_service.enums.RequirementType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DigitalConsentRequirementResponse {

    private Long id;
    private RequirementType type;
    private Boolean enabled;
    private Boolean mandatory;

    private Boolean mobileNumberOwnershipRequired;

    private Boolean digitalCreditScore;

    private Boolean dataConsentRequired;
}
