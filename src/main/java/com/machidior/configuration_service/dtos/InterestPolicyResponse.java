package com.machidior.configuration_service.dtos;

import com.machidior.configuration_service.enums.DayCountConversion;
import com.machidior.configuration_service.enums.InterestAccrualMethod;
import com.machidior.configuration_service.enums.InterestType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InterestPolicyResponse {

    private Long id;

    private InterestType interestType;

    private BigDecimal annualInterestRate;

    private BigDecimal minInterestRate;
    private BigDecimal maxInterestRate;

    private Boolean allowedRateOverride;

    private InterestAccrualMethod accrualMethod;

    private DayCountConversion dayCountConversion;

    private Boolean accrueDuringGracePeriod;

    private Boolean capitalizedInterest;

}
