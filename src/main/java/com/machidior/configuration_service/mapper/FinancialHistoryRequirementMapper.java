package com.machidior.configuration_service.mapper;

import com.machidior.configuration_service.dtos.request.requirement.FinancialHistoryRequirementRequest;
import com.machidior.configuration_service.dtos.response.requirement.FinancialHistoryRequirementResponse;
import com.machidior.configuration_service.enums.RequirementType;
import com.machidior.configuration_service.product.requirement.FinancialHistoryRequirement;
import com.machidior.configuration_service.product.LoanProductVersion;
import org.springframework.stereotype.Component;

@Component
public class FinancialHistoryRequirementMapper {

    public FinancialHistoryRequirement toEntity(FinancialHistoryRequirementRequest request, LoanProductVersion productVersion) {
        return FinancialHistoryRequirement.builder()
                .productVersion(productVersion)
                .type(RequirementType.FINANCIAL_HISTORY)
                .enabled(request.getEnabled())
                .mandatory(request.getMandatory())
                .bankStatementMonths(request.getBankStatementMonths())
                .creditHistoryCheckRequired(request.getCreditHistoryCheckRequired())
                .mobileMoneyStatementRequired(request.getMobileMoneyStatementRequired())
                .bankStatementRequired(request.getBankStatementRequired())
                .mobileMoneyStatementMonths(request.getMobileMoneyStatementMonths())
                .minCreditScore(request.getMinCreditScore())
                .build();
    }

    public FinancialHistoryRequirementResponse toResponse(FinancialHistoryRequirement requirement) {
        return FinancialHistoryRequirementResponse.builder()
                .id(requirement.getId())
                .type(requirement.getType())
                .enabled(requirement.getEnabled())
                .mandatory(requirement.getMandatory())
                .bankStatementRequired(requirement.getBankStatementRequired())
                .creditHistoryCheckRequired(requirement.getCreditHistoryCheckRequired())
                .mobileMoneyStatementRequired(requirement.getMobileMoneyStatementRequired())
                .bankStatementMonths(requirement.getBankStatementMonths())
                .mobileMoneyStatementMonths(requirement.getMobileMoneyStatementMonths())
                .minCreditScore(requirement.getMinCreditScore())
                .build();
    }
}
