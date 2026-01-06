package com.machidior.configuration_service.mapper;

import com.machidior.configuration_service.dtos.BusinessRequirementRequest;
import com.machidior.configuration_service.dtos.BusinessRequirementResponse;
import com.machidior.configuration_service.enums.RequirementType;
import com.machidior.configuration_service.model.BusinessRequirement;
import com.machidior.configuration_service.model.LoanProductVersion;
import org.springframework.stereotype.Component;

@Component
public class BusinessRequirementMapper {

    public BusinessRequirement toEntity(BusinessRequirementRequest request, LoanProductVersion productVersion) {
        return BusinessRequirement.builder()
                .productVersion(productVersion)
                .type(RequirementType.BUSINESS_DETAILS)
                .enabled(request.getEnabled())
                .mandatory(request.getMandatory())
                .registrationRequired(request.getRegistrationRequired())
                .businessLicenseRequired(request.getBusinessLicenseRequired())
                .cashFlowStatementRequired(request.getCashFlowStatementRequired())
                .bankStatementRequired(request.getBankStatementRequired())
                .tinCertificateRequired(request.getTinCertificateRequired())
                .tinNumberRequired(request.getTinNumberRequired())
                .insuranceComprehensiveCoverRequired(request.getInsuranceComprehensiveCoverRequired())
                .minYearsInOperation(request.getMinYearsInOperation())
                .minAverageMonthlyTurnOver(request.getMinAverageMonthlyTurnOver())
                .build();
    }

    public BusinessRequirementResponse toResponse(BusinessRequirement requirement) {
        return BusinessRequirementResponse.builder()
                .id(requirement.getId())
                .type(requirement.getType())
                .enabled(requirement.getEnabled())
                .mandatory(requirement.getMandatory())
                .registrationRequired(requirement.getRegistrationRequired())
                .businessLicenseRequired(requirement.getBusinessLicenseRequired())
                .cashFlowStatementRequired(requirement.getCashFlowStatementRequired())
                .bankStatementRequired(requirement.getBankStatementRequired())
                .tinCertificateRequired(requirement.getTinCertificateRequired())
                .tinNumberRequired(requirement.getTinNumberRequired())
                .insuranceComprehensiveCoverRequired(requirement.getInsuranceComprehensiveCoverRequired())
                .minYearsInOperation(requirement.getMinYearsInOperation())
                .minAverageMonthlyTurnOver(requirement.getMinAverageMonthlyTurnOver())
                .build();
    }
}
