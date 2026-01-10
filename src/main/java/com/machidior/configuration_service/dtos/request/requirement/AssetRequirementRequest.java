package com.machidior.configuration_service.dtos.request.requirement;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssetRequirementRequest{

    private Long productVersionId;
    private Boolean enabled;
    private Boolean mandatory;

    private Boolean quotationRequired;
    private Boolean supplierVerificationRequired;
    private Boolean assetInsuranceRequired;
    private Boolean assetOwnershipTransferredToLender;
}
