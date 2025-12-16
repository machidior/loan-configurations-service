package com.machidior.configuration_service.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanProductChargesRequest {
    @NotNull(message = "Loan charges must have a specific loan product")
    private Long productId;
    @NotNull(message = "First application fee is not provided.")
    private BigDecimal firstApplicationFee;
    @NotNull(message = "Subsequent application fee is not provided.")
    private BigDecimal subsequentApplicationFee;
    @NotNull(message = "Loan insurance percentage is not provided.")
    private BigDecimal loanInsurancePercent;
    private BigDecimal groupInsurancePercent;
    private String extraRules;
}
