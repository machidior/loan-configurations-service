package com.machidior.configuration_service.mapper;

import com.machidior.configuration_service.dtos.CollateralRequirementRequest;
import com.machidior.configuration_service.dtos.CollateralRequirementResponse;
import com.machidior.configuration_service.enums.CollateralType;
import com.machidior.configuration_service.enums.RequirementType;
import com.machidior.configuration_service.exceptions.InvalidEnumException;
import com.machidior.configuration_service.model.CollateralRequirement;
import com.machidior.configuration_service.model.LoanProductVersion;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CollateralRequirementMapper {

    public CollateralRequirement toEntity(CollateralRequirementRequest request, LoanProductVersion productVersion) {
        return CollateralRequirement.builder()
                .productVersion(productVersion)
                .type(RequirementType.COLLATERAL)
                .enabled(request.getEnabled())
                .mandatory(request.getMandatory())
                .allowedTypes(parseCollateralType(request.getAllowedTypes()))
                .minCount(request.getMinCount())
                .maxCount(request.getMaxCount())
                .descriptionRequired(request.getDescriptionRequired())
                .ownershipProofRequired(request.getOwnershipProofRequired())
                .insuranceRequired(request.getInsuranceRequired())
                .valuationRequired(request.getValuationRequired())
                .photoRequired(request.getPhotoRequired())
                .minAverageMonthlyTurnOver(request.getMinAverageMonthlyTurnOver())
                .build();
    }

    public CollateralRequirementResponse toResponse(CollateralRequirement requirement) {
        return CollateralRequirementResponse.builder()
                .id(requirement.getId())
                .type(requirement.getType())
                .enabled(requirement.getEnabled())
                .mandatory(requirement.getMandatory())
                .allowedTypes(requirement.getAllowedTypes())
                .minCount(requirement.getMinCount())
                .maxCount(requirement.getMaxCount())
                .descriptionRequired(requirement.getDescriptionRequired())
                .ownershipProofRequired(requirement.getOwnershipProofRequired())
                .insuranceRequired(requirement.getInsuranceRequired())
                .valuationRequired(requirement.getValuationRequired())
                .photoRequired(requirement.getPhotoRequired())
                .minAverageMonthlyTurnOver(requirement.getMinAverageMonthlyTurnOver())
                .build();
    }

    private List<CollateralType> parseCollateralType(List<String> collateralTypes) {
        if (!collateralTypes.isEmpty()) {
            List<CollateralType> parsedCollateralTypes = null;
            for (String type: collateralTypes) {
                try {
                     parsedCollateralTypes.add(CollateralType.valueOf(type.toUpperCase()));
                } catch (InvalidEnumException e) {
                    throw new InvalidEnumException("Invalid collateral type: " + type);
                }
            }
            return parsedCollateralTypes;
        }
        return null;
    }
}
