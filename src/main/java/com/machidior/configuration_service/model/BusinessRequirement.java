package com.machidior.configuration_service.model;

import com.machidior.configuration_service.enums.RequirementType;
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
public class BusinessRequirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private LoanProductVersion productVersion;

    @Enumerated(EnumType.STRING)
    private RequirementType type;

    private Boolean enabled;

    private Boolean mandatory;

    private Boolean registrationRequired;
    private Boolean businessLicenseRequired;
    private Boolean cashFlowStatementRequired;
    private Boolean bankStatementRequired;
    private Boolean tinCertificateRequired;
    private Boolean tinNumberRequired;
    private Boolean insuranceComprehensiveCoverRequired;

    private Integer minYearsInOperation;

    private BigDecimal minAverageMonthlyTurnOver;
}
