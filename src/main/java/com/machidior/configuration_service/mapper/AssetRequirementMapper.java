package com.machidior.configuration_service.mapper;

import com.machidior.configuration_service.dtos.AssetRequirementRequest;
import com.machidior.configuration_service.dtos.AssetRequirementResponse;
import com.machidior.configuration_service.enums.RequirementType;
import com.machidior.configuration_service.model.AssetRequirement;
import com.machidior.configuration_service.model.LoanProductVersion;
import org.springframework.stereotype.Component;

@Component
public class AssetRequirementMapper {

    public AssetRequirement toEntity(AssetRequirementRequest request, LoanProductVersion productVersion) {
        return AssetRequirement.builder()
                .productVersion(productVersion)
                .type(RequirementType.ASSET)
                .enabled(request.getEnabled())
                .mandatory(request.getMandatory())
                .quotationRequired(request.getQuotationRequired())
                .supplierVerificationRequired(request.getSupplierVerificationRequired())
                .assetInsuranceRequired(request.getAssetInsuranceRequired())
                .assetOwnershipTransferredToLender(request.getAssetOwnershipTransferredToLender())
                .build();
    }

    public AssetRequirementResponse toResponse(AssetRequirement requirement) {
        return AssetRequirementResponse.builder()
                .id(requirement.getId())
                .type(requirement.getType())
                .enabled(requirement.getEnabled())
                .mandatory(requirement.getMandatory())
                .quotationRequired(requirement.getQuotationRequired())
                .supplierVerificationRequired(requirement.getSupplierVerificationRequired())
                .assetInsuranceRequired(requirement.getAssetInsuranceRequired())
                .assetOwnershipTransferredToLender(requirement.getAssetOwnershipTransferredToLender())
                .build();
    }
}
