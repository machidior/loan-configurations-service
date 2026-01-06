package com.machidior.configuration_service.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DefaultPolicyResponse {

    private Long id;

    private Integer daysOverdueToDefault;

    private Boolean freezeInterestAccrual;

    private Boolean freezePenaltyAccrual;

    private Boolean blockFurtherDisbursement;

    private Boolean markAsNonPerforming;

    private Integer maxAllowedOverdueDays;

    private Boolean blockIfAnyDefaultedLoan;
}
