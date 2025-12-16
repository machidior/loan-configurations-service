package com.machidior.configuration_service.model;


import com.machidior.configuration_service.enums.LoanProductType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "loan_product_terms")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanProductTerms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    @Column(name = "min_amount")
    private BigDecimal minAmount;

    @Column(name = "max_amount")
    private BigDecimal maxAmount;

    @Column(name = "maximum_term_months")
    private Integer maximumTermMonths;

    @Column(name = "loan_fee_rate")
    private BigDecimal monthlyLoanFeeRate;

    @Column(name = "loan_fee_rate_2month")
    private BigDecimal loanFeeRatePer2Months;

    @Column(name = "interest_rate")
    private BigDecimal monthlyInterestRate;

    @Column(name = "min_group_members", nullable=true)
    private Integer minGroupMembers;

    @Column(name = "max_group_members", nullable = true)
    private Integer maxGroupMembers;

    @Column(name = "extra_rules", columnDefinition = "TEXT")
    private String extraRules;
}
