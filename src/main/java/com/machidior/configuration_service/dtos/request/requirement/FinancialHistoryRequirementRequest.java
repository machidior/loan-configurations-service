package com.machidior.configuration_service.dtos.request.requirement;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FinancialHistoryRequirementRequest {

    private Long productVersionId;
    private Boolean enabled;
    private Boolean mandatory;

    private Boolean bankStatementRequired;
    private Boolean creditHistoryCheckRequired;
    private Boolean mobileMoneyStatementRequired;

    private Integer bankStatementMonths;
    private Integer mobileMoneyStatementMonths;
    private Integer minCreditScore;
}
