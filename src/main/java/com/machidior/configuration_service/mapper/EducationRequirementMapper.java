package com.machidior.configuration_service.mapper;

import com.machidior.configuration_service.dtos.EducationRequirementRequest;
import com.machidior.configuration_service.dtos.EducationRequirementResponse;
import com.machidior.configuration_service.enums.RequirementType;
import com.machidior.configuration_service.model.EducationRequirement;
import com.machidior.configuration_service.model.LoanProductVersion;
import org.springframework.stereotype.Component;

@Component
public class EducationRequirementMapper {

    public EducationRequirement toEntity(EducationRequirementRequest request, LoanProductVersion productVersion) {
        return EducationRequirement.builder()
                .productVersion(productVersion)
                .type(RequirementType.EDUCATION)
                .enabled(request.getEnabled())
                .mandatory(request.getMandatory())
                .admissionLetterRequired(request.getAdmissionLetterRequired())
                .feeStructureRequired(request.getFeeStructureRequired())
                .sponsorRequired(request.getSponsorRequired())
                .guarantorRequired(request.getGuarantorRequired())
                .relatedEducationCertificateRequired(request.getRelatedEducationCertificateRequired())
                .build();
    }

    public EducationRequirementResponse toResponse(EducationRequirement requirement) {
        return EducationRequirementResponse.builder()
                .id(requirement.getId())
                .type(requirement.getType())
                .enabled(requirement.getEnabled())
                .mandatory(requirement.getMandatory())
                .admissionLetterRequired(requirement.getAdmissionLetterRequired())
                .feeStructureRequired(requirement.getFeeStructureRequired())
                .sponsorRequired(requirement.getSponsorRequired())
                .guarantorRequired(requirement.getGuarantorRequired())
                .relatedEducationCertificateRequired(requirement.getRelatedEducationCertificateRequired())
                .build();
    }
}
