package com.machidior.configuration_service.dtos.response.requirement;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequirementsResponse {
    private AgricultureRequirementResponse agricultureRequirement;
    private SolidarityRequirementResponse solidarityRequirement;
    private AssetRequirementResponse assetRequirement;
    private BusinessRequirementResponse businessRequirement;
    private CollateralRequirementResponse collateralRequirement;
    private DigitalConsentRequirementResponse digitalConsentRequirement;
    private EmploymentRequirementResponse employmentRequirement;
    private GuaranteeRequirementResponse guaranteeRequirement;
    private EducationRequirementResponse educationRequirement;
    private FinancialHistoryRequirementResponse financialHistoryRequirement;
    private HousingRequirementResponse housingRequirement;



//    private IdentityRequirementResponse identityRequirement;
//    private LegalRequirementResponse legalRequirement;
//    private ReferencesRequirementResponse referencesRequirement;
}
