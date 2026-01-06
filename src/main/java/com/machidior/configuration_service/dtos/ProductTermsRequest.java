package com.machidior.configuration_service.dtos;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductTermsRequest {

    private Long productVersionId;

    private BigDecimal minLoanAmount;

    private BigDecimal maxLoanAmount;

    private BigDecimal firstTimeBorrowerMaxAmount;

    private Integer minTenure;

    private Integer maxTenure;

    private String tenureUnit;

    private String installmentFrequency;

    private Integer minInstallments;
    private Integer maxInstallments;

    private Integer repaymentDayOfTheMonth;

    private Integer graceDaysBeforeFirstRepayment;

    private Boolean allowedCustomTenure;
    private Boolean allowedCustomAmount;

    private Boolean allowTopUp;

    private Boolean allowedRestructuring;

    private Boolean allowEarlyRepayment;
    private Boolean chargeEarlyRepaymentFee;

    private Boolean allowPartialDisbursement;
    private Integer maxDisbursementDaysAfterApproval;
    private Integer maxDisbursementTranches;
    private String disbursementMethod;

    private Boolean requireCreditBureauCheck;
    private Integer maxAllowedActiveLoans;

    private Boolean requireManualApproval;
    private Boolean allowAutoApproval;
    private Integer approvalLevelCount;

    private Boolean allowBranchesOverride;

    private String currency;

}
