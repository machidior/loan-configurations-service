package com.machidior.configuration_service.dtos;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusinessRequirementRequest {

    private Long productVersionId;
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
