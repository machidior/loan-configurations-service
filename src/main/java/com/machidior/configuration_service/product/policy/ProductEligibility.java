package com.machidior.configuration_service.product.policy;

import com.machidior.configuration_service.enums.ClientType;
import com.machidior.configuration_service.product.LoanProductVersion;
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
public class ProductEligibility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    private LoanProductVersion productVersion;

    private Integer minApplicantAge;
    private Integer maxApplicantAge;

    private BigDecimal minMonthlyIncome;
    private Integer minBusinessAgeMonths;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<ClientType> allowedClientTypes;

    private Integer minEmploymentDurationMonths;

    @ElementCollection
    private List<String> allowedRegions;

    private Boolean allowExistingBorrowers;
    private Boolean allowFirstTimeBorrowers;
}
