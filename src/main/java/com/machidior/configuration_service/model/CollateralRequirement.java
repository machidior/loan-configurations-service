package com.machidior.configuration_service.model;

import com.machidior.configuration_service.enums.CollateralType;
import com.machidior.configuration_service.enums.RequirementType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CollateralRequirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private LoanProductVersion productVersion;

    @Enumerated(EnumType.STRING)
    private RequirementType type;

    private Boolean enabled;

    private Boolean mandatory;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<CollateralType> allowedTypes;

    private Integer minCount;
    private Integer maxCount;

    private Boolean descriptionRequired;
    private Boolean ownershipProofRequired;
    private Boolean insuranceRequired;
    private Boolean valuationRequired;
    private Boolean photoRequired;

    private BigDecimal minAverageMonthlyTurnOver;

}
