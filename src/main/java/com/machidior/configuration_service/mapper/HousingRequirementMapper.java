package com.machidior.configuration_service.mapper;

import com.machidior.configuration_service.dtos.request.requirement.HousingRequirementRequest;
import com.machidior.configuration_service.dtos.response.requirement.HousingRequirementResponse;
import com.machidior.configuration_service.enums.RequirementType;
import com.machidior.configuration_service.product.requirement.HousingRequirement;
import com.machidior.configuration_service.product.LoanProductVersion;
import org.springframework.stereotype.Component;

@Component
public class HousingRequirementMapper {

    public HousingRequirement toEntity(HousingRequirementRequest request, LoanProductVersion productVersion) {
        return HousingRequirement.builder()
                .productVersion(productVersion)
                .type(RequirementType.HOUSING)
                .enabled(request.getEnabled())
                .mandatory(request.getMandatory())
                .ownershipProofRequired(request.getOwnershipProofRequired())
                .buildingPlanRequired(request.getBuildingPlanRequired())
                .siteInspectionRequired(request.getSiteInspectionRequired())
                .valuationRequired(request.getValuationRequired())
                .maxLoanToValueRatio(request.getMaxLoanToValueRatio())
                .build();
    }

    public HousingRequirementResponse toResponse(HousingRequirement requirement) {
        return HousingRequirementResponse.builder()
                .id(requirement.getId())
                .type(requirement.getType())
                .enabled(requirement.getEnabled())
                .mandatory(requirement.getMandatory())
                .ownershipProofRequired(requirement.getOwnershipProofRequired())
                .buildingPlanRequired(requirement.getBuildingPlanRequired())
                .siteInspectionRequired(requirement.getSiteInspectionRequired())
                .valuationRequired(requirement.getValuationRequired())
                .maxLoanToValueRatio(requirement.getMaxLoanToValueRatio())
                .build();
    }
}
