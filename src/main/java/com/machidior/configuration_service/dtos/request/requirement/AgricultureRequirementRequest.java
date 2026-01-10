package com.machidior.configuration_service.dtos.request.requirement;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AgricultureRequirementRequest {

    private Long productVersionId;
    private Boolean enabled;
    private Boolean mandatory;

    private Boolean farmDetailsRequired;
    private Boolean productionCycleRequired;
    private Boolean offTakerAgreement;
    private Boolean farmInspectionRequired;

    private Integer minFarmSize;

}
