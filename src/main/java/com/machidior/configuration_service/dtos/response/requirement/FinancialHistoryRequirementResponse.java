package com.machidior.configuration_service.dtos.response.requirement;

import com.machidior.configuration_service.enums.RequirementType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FinancialHistoryRequirementResponse {

    private Long id;
    private RequirementType type;
    private Boolean enabled;
    private Boolean mandatory;

    private Boolean bankStatementRequired;
    private Boolean creditHistoryCheckRequired;
    private Boolean mobileMoneyStatementRequired;

    private Integer bankStatementMonths;
    private Integer mobileMoneyStatementMonths;
    private Integer minCreditScore;
}
