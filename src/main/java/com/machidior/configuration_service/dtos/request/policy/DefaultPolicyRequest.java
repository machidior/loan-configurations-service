package com.machidior.configuration_service.dtos.request.policy;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DefaultPolicyRequest {

    private Long productVersionId;

    private Integer daysOverdueToDefault;

    private Boolean freezeInterestAccrual;

    private Boolean freezePenaltyAccrual;

    private Boolean blockFurtherDisbursement;

    private Boolean markAsNonPerforming;

    private Integer maxAllowedOverdueDays;

    private Boolean blockIfAnyDefaultedLoan;
}
