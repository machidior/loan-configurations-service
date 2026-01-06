package com.machidior.configuration_service.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DigitalConsentRequirementRequest {

    private Long productVersionId;
    private Boolean enabled;
    private Boolean mandatory;

    private Boolean mobileNumberOwnershipRequired;

    private Boolean digitalCreditScore;

    private Boolean dataConsentRequired;
}
