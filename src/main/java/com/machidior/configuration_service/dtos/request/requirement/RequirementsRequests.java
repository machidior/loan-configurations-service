package com.machidior.configuration_service.dtos.request.requirement;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequirementsRequests {
    private AgricultureRequirementRequest agricultureRequirement;
    private SolidarityRequirementRequest solidarityRequirement;
    private AssetRequirementRequest assetRequirement;
    private CollateralRequirementRequest collateralRequirement;
    private GuaranteeRequirementRequest guaranteeRequirement;
    private EmploymentRequirementRequest employmentRequirement;
    private BusinessRequirementRequest businessRequirement;
    private DigitalConsentRequirementRequest digitalConsentRequirement;
    private EducationRequirementRequest educationRequirement;
    private FinancialHistoryRequirementRequest financialHistoryRequirement;
    private HousingRequirementRequest housingRequirement;
}
