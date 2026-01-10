package com.machidior.configuration_service.product.clone.policy;

import com.machidior.configuration_service.product.clone.AbstractProductConfigCloner;
import com.machidior.configuration_service.product.policy.ProductTerms;
import com.machidior.configuration_service.product.LoanProductVersion;
import com.machidior.configuration_service.repository.ProductTermsRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductTermsCloner
        extends AbstractProductConfigCloner<ProductTerms> {

    private final ProductTermsRepository repository;

    public ProductTermsCloner(ProductTermsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ProductTerms> findByVersion(
            LoanProductVersion sourceVersion) {
        return repository.findAllByProductVersion(sourceVersion);
    }

    @Override
    public ProductTerms clone(
            ProductTerms source,
            LoanProductVersion targetVersion) {

        ProductTerms copy = new ProductTerms();
        copy.setProductVersion(targetVersion);

        copy.setMinLoanAmount(source.getMinLoanAmount());
        copy.setMaxLoanAmount(source.getMaxLoanAmount());
        copy.setFirstTimeBorrowerMaxAmount(
                source.getFirstTimeBorrowerMaxAmount());
        copy.setMinTenure(source.getMinTenure());
        copy.setMaxTenure(source.getMaxTenure());
        copy.setTenureUnit(source.getTenureUnit());
        copy.setInstallmentFrequency(source.getInstallmentFrequency());
        copy.setMinInstallments(source.getMinInstallments());
        copy.setMaxInstallments(source.getMaxInstallments());
        copy.setRepaymentDayOfTheMonth(
                source.getRepaymentDayOfTheMonth());
        copy.setGraceDaysBeforeFirstRepayment(
                source.getGraceDaysBeforeFirstRepayment());
        copy.setAllowedCustomTenure(source.getAllowedCustomTenure());
        copy.setAllowedCustomAmount(source.getAllowedCustomAmount());
        copy.setAllowTopUp(source.getAllowTopUp());
        copy.setAllowedRestructuring(source.getAllowedRestructuring());
        copy.setAllowEarlyRepayment(source.getAllowEarlyRepayment());
        copy.setChargeEarlyRepaymentFee(
                source.getChargeEarlyRepaymentFee());
        copy.setAllowPartialDisbursement(
                source.getAllowPartialDisbursement());
        copy.setMaxDisbursementDaysAfterApproval(
                source.getMaxDisbursementDaysAfterApproval());
        copy.setMaxDisbursementTranches(
                source.getMaxDisbursementTranches());
        copy.setDisbursementMethod(source.getDisbursementMethod());
        copy.setRequireCreditBureauCheck(
                source.getRequireCreditBureauCheck());
        copy.setMaxAllowedActiveLoans(
                source.getMaxAllowedActiveLoans());
        copy.setRequireManualApproval(
                source.getRequireManualApproval());
        copy.setAllowAutoApproval(source.getAllowAutoApproval());
        copy.setApprovalLevelCount(source.getApprovalLevelCount());
        copy.setAllowBranchesOverride(
                source.getAllowBranchesOverride());
        copy.setCurrency(source.getCurrency());

        return copy;
    }

    @Override
    public void save(ProductTerms entity) {
        repository.save(entity);
    }
}

