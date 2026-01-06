package com.machidior.configuration_service.model;

import com.machidior.configuration_service.enums.RequirementType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssetRequirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private LoanProductVersion productVersion;

    @Enumerated(EnumType.STRING)
    private RequirementType type;

    private Boolean enabled;

    private Boolean mandatory;

    private Boolean quotationRequired;
    private Boolean supplierVerificationRequired;
    private Boolean assetInsuranceRequired;
    private Boolean assetOwnershipTransferredToLender;
}
