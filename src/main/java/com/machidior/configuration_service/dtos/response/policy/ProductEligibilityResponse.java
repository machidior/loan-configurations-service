package com.machidior.configuration_service.dtos.response.policy;

import com.machidior.configuration_service.enums.ClientType;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductEligibilityResponse {

    private Long id;

    private Integer minApplicantAge;
    private Integer maxApplicantAge;

    private BigDecimal minMonthlyIncome;
    private Integer minBusinessAgeMonths;

    private List<ClientType> allowedClientTypes;

    private Integer minEmploymentDurationMonths;

    private List<String> allowedRegions;

    private Boolean allowExistingBorrowers;
    private Boolean allowFirstTimeBorrowers;
}
