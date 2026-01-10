package com.machidior.configuration_service.dtos.response.requirement;

import com.machidior.configuration_service.enums.RequirementType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AgricultureRequirementResponse {

    private Long id;
    private RequirementType type;
    private Boolean enabled;
    private Boolean mandatory;

    private Boolean farmDetailsRequired;
    private Boolean productionCycleRequired;
    private Boolean offTakerAgreement;
    private Boolean farmInspectionRequired;

    private Integer minFarmSize;
}
