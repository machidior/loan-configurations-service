package com.machidior.configuration_service.model;


import com.machidior.configuration_service.enums.DisbursementMethod;
import com.machidior.configuration_service.enums.InstallmentFrequency;
import com.machidior.configuration_service.enums.TenureUnit;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "loan_product_terms")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductTerms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    private LoanProductVersion productVersion;


    @Column(nullable = false)
    private BigDecimal minLoanAmount;

    @Column(nullable = false)
    private BigDecimal maxLoanAmount;

    private BigDecimal firstTimeBorrowerMaxAmount;

    @Column(nullable = false)
    private Integer minTenure;

    @Column(nullable = false)
    private Integer maxTenure;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TenureUnit tenureUnit;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InstallmentFrequency installmentFrequency;

    private Integer minInstallments;
    private Integer maxInstallments;

    private Integer repaymentDayOfTheMonth;

    private Integer graceDaysBeforeFirstRepayment;

    private Boolean allowedCustomTenure = false;
    private Boolean allowedCustomAmount = false;

    private Boolean allowTopUp = false;

    private Boolean allowedRestructuring = false;

    private Boolean allowEarlyRepayment = true;
    private Boolean chargeEarlyRepaymentFee = false;

    private Boolean allowPartialDisbursement = false;
    private Integer maxDisbursementDaysAfterApproval;
    private Integer maxDisbursementTranches;
    @Enumerated(EnumType.STRING)
    private DisbursementMethod disbursementMethod;

    private Boolean requireCreditBureauCheck;
    private Integer maxAllowedActiveLoans;

    private Boolean requireManualApproval;
    private Boolean allowAutoApproval;
    private Integer approvalLevelCount;

    private Boolean allowBranchesOverride;

    private String currency;

}
