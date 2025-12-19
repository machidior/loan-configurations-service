package com.machidior.configuration_service.model;


import com.machidior.configuration_service.enums.LoanProductType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private LoanProduct loanProduct;

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

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
