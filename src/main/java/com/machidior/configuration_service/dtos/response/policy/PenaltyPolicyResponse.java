package com.machidior.configuration_service.dtos.response.policy;

import com.machidior.configuration_service.enums.PenaltyCalculationMethod;
import com.machidior.configuration_service.enums.PenaltyFrequency;
import com.machidior.configuration_service.enums.PenaltyType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PenaltyPolicyResponse {

    private Long id;

    private Integer graceDaysAfterDueDate;

    private PenaltyType penaltyType;

    private PenaltyCalculationMethod calculationMethod;

    private BigDecimal fixedAmount;

    private BigDecimal percentage;

    private BigDecimal maxPenaltyAmount;

    private PenaltyFrequency frequency;

    private Boolean compoundPenalty;

    private Integer maxPenaltyDays;

}
