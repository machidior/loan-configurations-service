package com.machidior.configuration_service.dtos;

import com.machidior.configuration_service.enums.RequirementType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EducationRequirementResponse {

    private Long id;
    private RequirementType type;
    private Boolean enabled;
    private Boolean mandatory;

    private Boolean admissionLetterRequired;
    private Boolean feeStructureRequired;
    private Boolean sponsorRequired;
    private Boolean guarantorRequired;
    private Boolean relatedEducationCertificateRequired;
}
