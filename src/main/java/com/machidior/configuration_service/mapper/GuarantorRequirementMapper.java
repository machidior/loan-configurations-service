package com.machidior.configuration_service.mapper;

import com.machidior.configuration_service.dtos.GuaranteeRequirementRequest;
import com.machidior.configuration_service.dtos.GuaranteeRequirementResponse;
import com.machidior.configuration_service.enums.RequirementType;
import com.machidior.configuration_service.model.GuarantorRequirement;
import com.machidior.configuration_service.model.LoanProductVersion;
import org.springframework.stereotype.Component;

@Component
public class GuarantorRequirementMapper {

    public GuarantorRequirement toEntity(GuaranteeRequirementRequest request, LoanProductVersion productVersion) {
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
                .minGuarantorIncome(request.getMinGuarantorIncome())
                .build();
    }

    public GuaranteeRequirementResponse toResponse(GuarantorRequirement requirement) {
        return GuaranteeRequirementResponse.builder()
                .id(requirement.getId())
                .type(requirement.getType())
                .enabled(requirement.getEnabled())
                .mandatory(requirement.getMandatory())
                .minGuarantors(requirement.getMinGuarantors())
                .maxGuarantors(requirement.getMaxGuarantors())
                .guarantorIncomeProofRequired(requirement.getGuarantorIncomeProofRequired())
                .guarantorEmploymentRequired(requirement.getGuarantorEmploymentRequired())
                .guarantorRelationRequired(requirement.getGuarantorRelationRequired())
                .minGuarantorIncome(requirement.getMinGuarantorIncome())
                .build();
    }
}
