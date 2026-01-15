package com.machidior.configuration_service.product.requirement;


import com.machidior.configuration_service.enums.RequirementType;
import com.machidior.configuration_service.product.LoanProductVersion;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GuarantorRequirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private LoanProductVersion productVersion;

    @Enumerated(EnumType.STRING)
    private RequirementType type;

    private Boolean enabled;

    private Boolean mandatory;

    private Integer minGuarantors;
    private Integer maxGuarantors;

    private Boolean guarantorIncomeProofRequired;
    private Boolean guarantorEmploymentRequired;
    private Boolean guarantorRelationRequired;

    private Boolean passportPhotoRequired;
    private Boolean idDocumentRequired;
    private Boolean guarantorConsentRequired;

    private BigDecimal minGuarantorIncome;
}
