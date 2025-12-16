package com.machidior.configuration_service.dtos;


import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanProductTermsRequest {

    @NotNull(message = "This term has no specific loan product")
    private Long productId;
    @NotNull(message = "Minimum Amount is required.")
    private BigDecimal minAmount;
    @NotNull(message = "Maximum amount is required.")
    private BigDecimal maxAmount;
    @NotNull(message = "Maximum term months is required")
    private Integer maximumTermMonths;
    @NotNull(message = "Monthly loan fee rate is not provided.")
    private BigDecimal monthlyLoanFeeRate;
    private BigDecimal loanFeeRatePer2Months;
    @NotNull(message = "Monthly interest rate is required")
    private BigDecimal monthlyInterestRate;
    private Integer minGroupMembers;
    private Integer maxGroupMembers;
    private String extraRules;
}
