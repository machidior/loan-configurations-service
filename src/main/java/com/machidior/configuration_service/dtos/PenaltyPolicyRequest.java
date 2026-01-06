package com.machidior.configuration_service.dtos;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PenaltyPolicyRequest {

    private Long productVersionId;

    private Integer graceDaysAfterDueDate;

    private String penaltyType;

    private String calculationMethod;

    private BigDecimal fixedAmount;

    private BigDecimal percentage;

    private BigDecimal maxPenaltyAmount;

    private String frequency;

    private Boolean compoundPenalty;

    private Integer maxPenaltyDays;

}
