package com.machidior.configuration_service.dtos.request.requirement;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SolidarityRequirementRequest {

    private Long productVersionId;
    private Boolean enabled;
    private Boolean mandatory;

    private Boolean groupRegistrationRequired;
    private Boolean groupGuaranteeRequired;
    private Boolean groupMeetingRecordsRequired;

    private Integer minGroupMembers;
    private Integer maxGroupMembers;
}
