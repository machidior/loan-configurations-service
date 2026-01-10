package com.machidior.configuration_service.dtos.response.requirement;

import com.machidior.configuration_service.enums.RequirementType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmploymentRequirementResponse {

    private Long id;
    private RequirementType type;
    private Boolean enabled;
    private Boolean mandatory;

    private Boolean jobContractRequired;
    private Boolean paySlipRequired;
    private Boolean payrollDeductionRequired;
    private Boolean bankStatementRequired;

    private Integer minMonthsEmployed;

    private BigDecimal minNetSalary;
}
