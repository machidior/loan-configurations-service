package com.machidior.configuration_service.model;

import com.machidior.configuration_service.enums.LoanProductType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "loan_product_charges")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanProductCharges {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    @Column(name = "first_application_fee")
    private BigDecimal firstApplicationFee;

    @Column(name = "subsequent_application_fee")
    private BigDecimal subsequentApplicationFee;

    @Column(name = "loan_insurance_percent")
    private BigDecimal loanInsurancePercent;

    @Column(name = "group_insurance_percent")
    private BigDecimal groupInsurancePercent;

    @Column(name = "extra_rules", columnDefinition = "TEXT")
    private String extraRules;
}
