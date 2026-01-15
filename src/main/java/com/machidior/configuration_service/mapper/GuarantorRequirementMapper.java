package com.machidior.configuration_service.mapper;

import com.machidior.configuration_service.dtos.request.requirement.GuarantorRequirementRequest;
import com.machidior.configuration_service.dtos.response.requirement.GuarantorRequirementResponse;
import com.machidior.configuration_service.enums.RequirementType;
import com.machidior.configuration_service.product.requirement.GuarantorRequirement;
import com.machidior.configuration_service.product.LoanProductVersion;
import org.springframework.stereotype.Component;

@Component
public class GuarantorRequirementMapper {

    public GuarantorRequirement toEntity(GuarantorRequirementRequest request, LoanProductVersion productVersion) {
        return GuarantorRequirement.builder()
                .productVersion(productVersion)
                .type(RequirementType.GUARANTOR)
                .enabled(request.getEnabled())
                .mandatory(request.getMandatory())
                .maxGuarantors(request.getMaxGuarantors())
                .minGuarantors(request.getMinGuarantors())
                .guarantorIncomeProofRequired(request.getGuarantorIncomeProofRequired())
                .guarantorEmploymentRequired(request.getGuarantorEmploymentRequired())
                .guarantorRelationRequired(request.getGuarantorRelationRequired())
                .passportPhotoRequired(request.getPassportPhotoRequired())
                .idDocumentRequired(request.getIdDocumentRequired())
                .guarantorConsentRequired(request.getGuarantorConsentRequired())
                .minGuarantorIncome(request.getMinGuarantorIncome())
                .build();
    }

    public GuarantorRequirementResponse toResponse(GuarantorRequirement requirement) {
        return GuarantorRequirementResponse.builder()
                .id(requirement.getId())
                .type(requirement.getType())
                .enabled(requirement.getEnabled())
                .mandatory(requirement.getMandatory())
                .minGuarantors(requirement.getMinGuarantors())
                .maxGuarantors(requirement.getMaxGuarantors())
                .guarantorIncomeProofRequired(requirement.getGuarantorIncomeProofRequired())
                .guarantorEmploymentRequired(requirement.getGuarantorEmploymentRequired())
                .guarantorRelationRequired(requirement.getGuarantorRelationRequired())
                .passportPhotoRequired(requirement.getPassportPhotoRequired())
                .idDocumentRequired(requirement.getIdDocumentRequired())
                .guarantorConsentRequired(requirement.getGuarantorConsentRequired())
                .minGuarantorIncome(requirement.getMinGuarantorIncome())
                .build();
    }
}
