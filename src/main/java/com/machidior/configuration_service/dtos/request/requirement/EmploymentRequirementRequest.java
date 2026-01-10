package com.machidior.configuration_service.dtos.request.requirement;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmploymentRequirementRequest {

    private Long productVersionId;
    private Boolean enabled;
    private Boolean mandatory;

    private Boolean jobContractRequired;
    private Boolean paySlipRequired;
    private Boolean payrollDeductionRequired;
    private Boolean bankStatementRequired;

    private Integer minMonthsEmployed;

    private BigDecimal minNetSalary;
}
