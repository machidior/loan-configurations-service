package com.machidior.configuration_service.product.clone.policy;

import com.machidior.configuration_service.product.clone.AbstractProductConfigCloner;
import com.machidior.configuration_service.product.policy.PenaltyPolicy;
import com.machidior.configuration_service.product.LoanProductVersion;
import com.machidior.configuration_service.repository.PenaltyPolicyRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PenaltyPolicyCloner
        extends AbstractProductConfigCloner<PenaltyPolicy> {

    private final PenaltyPolicyRepository repository;

    public PenaltyPolicyCloner(PenaltyPolicyRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PenaltyPolicy> findByVersion(
            LoanProductVersion sourceVersion) {
        return repository.findAllByProductVersion(sourceVersion);
    }

    @Override
    public PenaltyPolicy clone(
            PenaltyPolicy source,
            LoanProductVersion targetVersion) {

        PenaltyPolicy copy = new PenaltyPolicy();
        copy.setProductVersion(targetVersion);

        copy.setGraceDaysAfterDueDate(source.getGraceDaysAfterDueDate());
        copy.setPenaltyType(source.getPenaltyType());
        copy.setCalculationMethod(source.getCalculationMethod());
        copy.setFixedAmount(source.getFixedAmount());
        copy.setPercentage(source.getPercentage());
        copy.setMaxPenaltyAmount(source.getMaxPenaltyAmount());
        copy.setFrequency(source.getFrequency());
        copy.setCompoundPenalty(source.getCompoundPenalty());
        copy.setMaxPenaltyDays(source.getMaxPenaltyDays());

        return copy;
    }

    @Override
    public void save(PenaltyPolicy entity) {
        repository.save(entity);
    }
}
