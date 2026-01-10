package com.machidior.configuration_service.product.clone.policy;

import com.machidior.configuration_service.product.clone.AbstractProductConfigCloner;
import com.machidior.configuration_service.product.policy.InterestPolicy;
import com.machidior.configuration_service.product.LoanProductVersion;
import com.machidior.configuration_service.repository.InterestPolicyRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InterestPolicyCloner
        extends AbstractProductConfigCloner<InterestPolicy> {

    private final InterestPolicyRepository repository;

    public InterestPolicyCloner(InterestPolicyRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<InterestPolicy> findByVersion(
            LoanProductVersion sourceVersion) {
        return repository.findAllByProductVersion(sourceVersion);
    }

    @Override
    public InterestPolicy clone(
            InterestPolicy source,
            LoanProductVersion targetVersion) {

        InterestPolicy copy = new InterestPolicy();
        copy.setProductVersion(targetVersion);

        copy.setInterestType(source.getInterestType());
        copy.setAnnualInterestRate(source.getAnnualInterestRate());
        copy.setMinInterestRate(source.getMinInterestRate());
        copy.setMaxInterestRate(source.getMaxInterestRate());
        copy.setAllowedRateOverride(source.getAllowedRateOverride());
        copy.setAccrualMethod(source.getAccrualMethod());
        copy.setDayCountConversion(source.getDayCountConversion());
        copy.setAccrueDuringGracePeriod(source.getAccrueDuringGracePeriod());
        copy.setCapitalizedInterest(source.getCapitalizedInterest());

        return copy;
    }

    @Override
    public void save(InterestPolicy entity) {
        repository.save(entity);
    }
}
