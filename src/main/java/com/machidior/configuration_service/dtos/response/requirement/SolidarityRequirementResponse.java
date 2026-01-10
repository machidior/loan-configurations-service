package com.machidior.configuration_service.dtos.response.requirement;

import com.machidior.configuration_service.enums.RequirementType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SolidarityRequirementResponse {

    private Long id;
    private RequirementType type;
    private Boolean enabled;
    private Boolean mandatory;

    private Boolean groupRegistrationRequired;
    private Boolean groupGuaranteeRequired;
    private Boolean groupMeetingRecordsRequired;

    private Integer minGroupMembers;
    private Integer maxGroupMembers;
}
