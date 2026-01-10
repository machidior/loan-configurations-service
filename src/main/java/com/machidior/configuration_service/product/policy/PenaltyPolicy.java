package com.machidior.configuration_service.product.policy;

import com.machidior.configuration_service.enums.PenaltyCalculationMethod;
import com.machidior.configuration_service.enums.PenaltyFrequency;
import com.machidior.configuration_service.enums.PenaltyType;
import com.machidior.configuration_service.product.LoanProductVersion;
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
public class PenaltyPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    private LoanProductVersion productVersion;

    @Column(nullable = false)
    private Integer graceDaysAfterDueDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PenaltyType penaltyType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PenaltyCalculationMethod calculationMethod;

    private BigDecimal fixedAmount;

    private BigDecimal percentage;

    private BigDecimal maxPenaltyAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PenaltyFrequency frequency;

    private Boolean compoundPenalty = false;

    private Integer maxPenaltyDays;

}
