package com.machidior.configuration_service.mapper;

import com.machidior.configuration_service.dtos.DigitalConsentRequirementRequest;
import com.machidior.configuration_service.dtos.DigitalConsentRequirementResponse;
import com.machidior.configuration_service.enums.RequirementType;
import com.machidior.configuration_service.model.DigitalConsentRequirement;
import com.machidior.configuration_service.model.LoanProductVersion;
import org.springframework.stereotype.Component;

@Component
public class DigitalConsentRequirementMapper {

    public DigitalConsentRequirement toEntity(DigitalConsentRequirementRequest request, LoanProductVersion productVersion) {
        return DigitalConsentRequirement.builder()
                .productVersion(productVersion)
                .type(RequirementType.DIGITAL_CONSENT)
                .enabled(request.getEnabled())
                .mandatory(request.getMandatory())
                .mobileNumberOwnershipRequired(request.getMobileNumberOwnershipRequired())
                .digitalCreditScore(request.getDigitalCreditScore())
                .dataConsentRequired(request.getDataConsentRequired())
                .build();
    }

    public DigitalConsentRequirementResponse toResponse(DigitalConsentRequirement requirement) {
        return DigitalConsentRequirementResponse.builder()
                .id(requirement.getId())
                .type(requirement.getType())
                .enabled(requirement.getEnabled())
                .mandatory(requirement.getMandatory())
                .mobileNumberOwnershipRequired(requirement.getMobileNumberOwnershipRequired())
                .digitalCreditScore(requirement.getDigitalCreditScore())
                .dataConsentRequired(requirement.getDataConsentRequired())
                .build();
    }
}
