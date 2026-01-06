package com.machidior.configuration_service.mapper;

import com.machidior.configuration_service.dtos.AgricultureRequirementRequest;
import com.machidior.configuration_service.dtos.AgricultureRequirementResponse;
import com.machidior.configuration_service.enums.RequirementType;
import com.machidior.configuration_service.model.AgricultureRequirement;
import com.machidior.configuration_service.model.LoanProductVersion;
import org.springframework.stereotype.Component;

@Component
public class AgricultureRequirementMapper {

    public AgricultureRequirement toEntity(AgricultureRequirementRequest request, LoanProductVersion productVersion) {
        return AgricultureRequirement.builder()
                .productVersion(productVersion)
                .type(RequirementType.AGRICULTURE)
                .enabled(request.getEnabled())
                .mandatory(request.getMandatory())
                .farmDetailsRequired(request.getFarmDetailsRequired())
                .productionCycleRequired(request.getProductionCycleRequired())
                .offTakerAgreement(request.getOffTakerAgreement())
                .farmInspectionRequired(request.getFarmInspectionRequired())
                .minFarmSize(request.getMinFarmSize())
                .build();
    }

    public AgricultureRequirementResponse toResponse(AgricultureRequirement agricultureRequirement) {
        return AgricultureRequirementResponse.builder()
                .id(agricultureRequirement.getId())
                .type(agricultureRequirement.getType())
                .enabled(agricultureRequirement.getEnabled())
                .mandatory(agricultureRequirement.getMandatory())
                .farmDetailsRequired(agricultureRequirement.getFarmDetailsRequired())
                .productionCycleRequired(agricultureRequirement.getProductionCycleRequired())
                .offTakerAgreement(agricultureRequirement.getOffTakerAgreement())
                .farmInspectionRequired(agricultureRequirement.getFarmInspectionRequired())
                .minFarmSize(agricultureRequirement.getMinFarmSize())
                .build();
    }
}
