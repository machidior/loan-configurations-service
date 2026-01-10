package com.machidior.configuration_service.mapper;

import com.machidior.configuration_service.dtos.request.policy.ProductTermsRequest;
import com.machidior.configuration_service.dtos.response.policy.ProductTermsResponse;
import com.machidior.configuration_service.enums.DisbursementMethod;
import com.machidior.configuration_service.enums.InstallmentFrequency;
import com.machidior.configuration_service.enums.TenureUnit;
import com.machidior.configuration_service.exceptions.InvalidEnumException;
import com.machidior.configuration_service.product.LoanProductVersion;
import com.machidior.configuration_service.product.policy.ProductTerms;
import org.springframework.stereotype.Component;

@Component
public class ProductTermsMapper {

    public ProductTerms toEntity(ProductTermsRequest request, LoanProductVersion productVersion) {
        return ProductTerms.builder()
                .productVersion(productVersion)
                .maxLoanAmount(request.getMaxLoanAmount())
                .minLoanAmount(request.getMinLoanAmount())
                .firstTimeBorrowerMaxAmount(request.getFirstTimeBorrowerMaxAmount())
                .minTenure(request.getMinTenure())
                .maxTenure(request.getMaxTenure())
                .tenureUnit(parseTenureUnit(request.getTenureUnit()))
                .installmentFrequency(parseInstallmentFrequency(request.getInstallmentFrequency()))
                .minInstallments(request.getMinInstallments())
                .maxInstallments(request.getMaxInstallments())
                .repaymentDayOfTheMonth(request.getRepaymentDayOfTheMonth())
                .graceDaysBeforeFirstRepayment(request.getGraceDaysBeforeFirstRepayment())
                .allowedCustomTenure(request.getAllowedCustomTenure())
                .allowedCustomAmount(request.getAllowedCustomAmount())
                .allowTopUp(request.getAllowTopUp())
                .allowedRestructuring(request.getAllowedRestructuring())
                .allowEarlyRepayment(request.getAllowEarlyRepayment())
                .chargeEarlyRepaymentFee(request.getChargeEarlyRepaymentFee())
                .allowPartialDisbursement(request.getAllowPartialDisbursement())
                .maxDisbursementDaysAfterApproval(request.getMaxDisbursementDaysAfterApproval())
                .maxDisbursementTranches(request.getMaxDisbursementTranches())
                .disbursementMethod(parseDisbursementMethod(request.getDisbursementMethod()))
                .requireCreditBureauCheck(request.getRequireCreditBureauCheck())
                .maxAllowedActiveLoans(request.getMaxAllowedActiveLoans())
                .requireManualApproval(request.getRequireManualApproval())
                .allowAutoApproval(request.getAllowAutoApproval())
                .approvalLevelCount(request.getApprovalLevelCount())
                .allowBranchesOverride(request.getAllowBranchesOverride())
                .currency(request.getCurrency())
                .build();
    }

    public ProductTermsResponse toResponse(ProductTerms terms) {
        return ProductTermsResponse.builder()
                .id(terms.getId())
                .minLoanAmount(terms.getMinLoanAmount())
                .maxLoanAmount(terms.getMaxLoanAmount())
                .firstTimeBorrowerMaxAmount(terms.getFirstTimeBorrowerMaxAmount())
                .minTenure(terms.getMinTenure())
                .maxTenure(terms.getMaxTenure())
                .tenureUnit(terms.getTenureUnit())
                .installmentFrequency(terms.getInstallmentFrequency())
                .minInstallments(terms.getMinInstallments())
                .maxInstallments(terms.getMaxInstallments())
                .repaymentDayOfTheMonth(terms.getRepaymentDayOfTheMonth())
                .graceDaysBeforeFirstRepayment(terms.getGraceDaysBeforeFirstRepayment())
                .allowedCustomTenure(terms.getAllowedCustomTenure())
                .allowedCustomAmount(terms.getAllowedCustomAmount())
                .allowTopUp(terms.getAllowTopUp())
                .allowedRestructuring(terms.getAllowedRestructuring())
                .allowEarlyRepayment(terms.getAllowEarlyRepayment())
                .chargeEarlyRepaymentFee(terms.getChargeEarlyRepaymentFee())
                .allowPartialDisbursement(terms.getAllowPartialDisbursement())
                .maxDisbursementDaysAfterApproval(terms.getMaxDisbursementDaysAfterApproval())
                .maxDisbursementTranches(terms.getMaxDisbursementTranches())
                .disbursementMethod(terms.getDisbursementMethod())
                .requireCreditBureauCheck(terms.getRequireCreditBureauCheck())
                .maxAllowedActiveLoans(terms.getMaxAllowedActiveLoans())
                .requireManualApproval(terms.getRequireManualApproval())
                .allowAutoApproval(terms.getAllowAutoApproval())
                .approvalLevelCount(terms.getApprovalLevelCount())
                .allowBranchesOverride(terms.getAllowBranchesOverride())
                .currency(terms.getCurrency())
                .build();
    }

    private TenureUnit parseTenureUnit(String unit) {
        try {
            return unit != null? TenureUnit.valueOf(unit.toUpperCase()): null;
        } catch (InvalidEnumException e) {
            throw new InvalidEnumException("Invalid tenure unit provided: " + unit);
        }
    }

    private InstallmentFrequency parseInstallmentFrequency(String frequency) {
        try {
            return frequency != null? InstallmentFrequency.valueOf(frequency.toUpperCase()): null;
        } catch (InvalidEnumException e) {
            throw new InvalidEnumException("Invalid installment frequency provided: " + frequency);
        }
    }

    private DisbursementMethod parseDisbursementMethod(String method) {
        try {
            return method != null? DisbursementMethod.valueOf(method.toUpperCase()): null;
        } catch (InvalidEnumException e) {
            throw new InvalidEnumException("Invalid disbursement method provided: " + method);
        }
    }
}
