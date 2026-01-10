package com.machidior.configuration_service.mapper;

import com.machidior.configuration_service.dtos.request.requirement.SolidarityRequirementRequest;
import com.machidior.configuration_service.dtos.response.requirement.SolidarityRequirementResponse;
import com.machidior.configuration_service.enums.RequirementType;
import com.machidior.configuration_service.product.LoanProductVersion;
import com.machidior.configuration_service.product.requirement.SolidarityRequirement;
import org.springframework.stereotype.Component;

@Component
public class SolidarityRequirementMapper {

    public SolidarityRequirement toEntity(SolidarityRequirementRequest request, LoanProductVersion productVersion) {
        return SolidarityRequirement.builder()
                .productVersion(productVersion)
                .type(RequirementType.SOLIDARITY)
                .enabled(request.getEnabled())
                .mandatory(request.getMandatory())
                .groupRegistrationRequired(request.getGroupRegistrationRequired())
                .groupGuaranteeRequired(request.getGroupGuaranteeRequired())
                .groupMeetingRecordsRequired(request.getGroupMeetingRecordsRequired())
                .minGroupMembers(request.getMinGroupMembers())
                .maxGroupMembers(request.getMaxGroupMembers())
                .build();
    }

    public SolidarityRequirementResponse toResponse(SolidarityRequirement requirement) {
        return SolidarityRequirementResponse.builder()
                .id(requirement.getId())
                .type(requirement.getType())
                .enabled(requirement.getEnabled())
                .mandatory(requirement.getMandatory())
                .groupRegistrationRequired(requirement.getGroupRegistrationRequired())
                .groupGuaranteeRequired(requirement.getGroupGuaranteeRequired())
                .groupMeetingRecordsRequired(requirement.getGroupMeetingRecordsRequired())
                .minGroupMembers(requirement.getMinGroupMembers())
                .maxGroupMembers(requirement.getMaxGroupMembers())
                .build();
    }

}
