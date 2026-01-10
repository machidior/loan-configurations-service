package com.machidior.configuration_service.product.clone.policy;

import com.machidior.configuration_service.product.clone.AbstractProductConfigCloner;
import com.machidior.configuration_service.product.policy.ProductEligibility;
import com.machidior.configuration_service.product.LoanProductVersion;
import com.machidior.configuration_service.repository.ProductEligibilityRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductEligibilityCloner
        extends AbstractProductConfigCloner<ProductEligibility> {

    private final ProductEligibilityRepository repository;

    public ProductEligibilityCloner(ProductEligibilityRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ProductEligibility> findByVersion(
            LoanProductVersion sourceVersion) {
        return repository.findAllByProductVersion(sourceVersion);
    }

    @Override
    public ProductEligibility clone(
            ProductEligibility source,
            LoanProductVersion targetVersion) {

        ProductEligibility copy = new ProductEligibility();
        copy.setProductVersion(targetVersion);

        copy.setMinApplicantAge(source.getMinApplicantAge());
        copy.setMaxApplicantAge(source.getMaxApplicantAge());
        copy.setMinMonthlyIncome(source.getMinMonthlyIncome());
        copy.setMinBusinessAgeMonths(source.getMinBusinessAgeMonths());
        copy.setAllowedClientTypes(
                source.getAllowedClientTypes() == null
                        ? null
                        : List.copyOf(source.getAllowedClientTypes()));
        copy.setMinEmploymentDurationMonths(
                source.getMinEmploymentDurationMonths());
        copy.setAllowedRegions(
                source.getAllowedRegions() == null
                        ? null
                        : List.copyOf(source.getAllowedRegions()));
        copy.setAllowExistingBorrowers(source.getAllowExistingBorrowers());
        copy.setAllowFirstTimeBorrowers(source.getAllowFirstTimeBorrowers());

        return copy;
    }

    @Override
    public void save(ProductEligibility entity) {
        repository.save(entity);
    }
}

