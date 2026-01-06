package com.machidior.configuration_service.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EducationRequirementRequest {

    private Long productVersionId;
    private Boolean enabled;
    private Boolean mandatory;

    private Boolean admissionLetterRequired;
    private Boolean feeStructureRequired;
    private Boolean sponsorRequired;
    private Boolean guarantorRequired;
    private Boolean relatedEducationCertificateRequired;
}
