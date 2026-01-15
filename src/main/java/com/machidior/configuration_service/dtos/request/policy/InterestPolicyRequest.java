package com.machidior.configuration_service.dtos.request.policy;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InterestPolicyRequest {

    private Long productVersionId;

    private String interestType;

    private BigDecimal monthlyInterestRate;

    private BigDecimal minInterestRate;
    private BigDecimal maxInterestRate;

    private Boolean allowedRateOverride;

    private String accrualMethod;

    private String dayCountConversion;

    private Boolean accrueDuringGracePeriod;

    private Boolean capitalizedInterest;

}
