package com.machidior.configuration_service.dtos;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductEligibilityRequest {

    private Long productVersionId;

    private Integer minApplicantAge;
    private Integer maxApplicantAge;

    private BigDecimal minMonthlyIncome;
    private Integer minBusinessAgeMonths;

    private List<String> allowedClientTypes;

    private Integer minEmploymentDurationMonths;

    private List<String> allowedRegions;

    private Boolean allowExistingBorrowers;
    private Boolean allowFirstTimeBorrowers;
}
