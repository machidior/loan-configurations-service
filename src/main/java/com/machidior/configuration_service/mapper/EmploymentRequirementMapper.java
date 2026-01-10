package com.machidior.configuration_service.mapper;

import com.machidior.configuration_service.dtos.request.requirement.EmploymentRequirementRequest;
import com.machidior.configuration_service.dtos.response.requirement.EmploymentRequirementResponse;
import com.machidior.configuration_service.enums.RequirementType;
import com.machidior.configuration_service.product.requirement.EmploymentRequirement;
import com.machidior.configuration_service.product.LoanProductVersion;
import org.springframework.stereotype.Component;

@Component
public class EmploymentRequirementMapper {

    public EmploymentRequirement toEntity(EmploymentRequirementRequest request, LoanProductVersion productVersion) {
        return EmploymentRequirement.builder()
                .productVersion(productVersion)
                .type(RequirementType.EMPLOYMENT_DETAILS)
                .enabled(request.getEnabled())
                .mandatory(request.getMandatory())
                .jobContractRequired(request.getJobContractRequired())
                .paySlipRequired(request.getPaySlipRequired())
                .payrollDeductionRequired(request.getPayrollDeductionRequired())
                .bankStatementRequired(request.getBankStatementRequired())
                .minMonthsEmployed(request.getMinMonthsEmployed())
                .minNetSalary(request.getMinNetSalary())
                .build();
    }

    public EmploymentRequirementResponse toResponse(EmploymentRequirement requirement) {
        return EmploymentRequirementResponse.builder()
                .id(requirement.getId())
                .type(requirement.getType())
                .enabled(requirement.getEnabled())
                .mandatory(requirement.getMandatory())
                .jobContractRequired(requirement.getJobContractRequired())
                .paySlipRequired(requirement.getPaySlipRequired())
                .payrollDeductionRequired(requirement.getPayrollDeductionRequired())
                .bankStatementRequired(requirement.getBankStatementRequired())
                .minMonthsEmployed(requirement.getMinMonthsEmployed())
                .minNetSalary(requirement.getMinNetSalary())
                .build();
    }
}
