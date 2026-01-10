package com.machidior.configuration_service.product.clone.policy;

import com.machidior.configuration_service.product.clone.AbstractProductConfigCloner;
import com.machidior.configuration_service.product.policy.DefaultPolicy;
import com.machidior.configuration_service.product.LoanProductVersion;
import com.machidior.configuration_service.repository.DefaultPolicyRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultPolicyCloner
        extends AbstractProductConfigCloner<DefaultPolicy> {

    private final DefaultPolicyRepository repository;

    public DefaultPolicyCloner(DefaultPolicyRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DefaultPolicy> findByVersion(
            LoanProductVersion sourceVersion) {
        return repository.findAllByProductVersion(sourceVersion);
    }

    @Override
    public DefaultPolicy clone(
            DefaultPolicy source,
            LoanProductVersion targetVersion) {

        DefaultPolicy copy = new DefaultPolicy();
        copy.setProductVersion(targetVersion);

        copy.setDaysOverdueToDefault(source.getDaysOverdueToDefault());
        copy.setFreezeInterestAccrual(source.getFreezeInterestAccrual());
        copy.setFreezePenaltyAccrual(source.getFreezePenaltyAccrual());
        copy.setBlockFurtherDisbursement(source.getBlockFurtherDisbursement());
        copy.setMarkAsNonPerforming(source.getMarkAsNonPerforming());
        copy.setMaxAllowedOverdueDays(source.getMaxAllowedOverdueDays());
        copy.setBlockIfAnyDefaultedLoan(source.getBlockIfAnyDefaultedLoan());

        return copy;
    }

    @Override
    public void save(DefaultPolicy entity) {
        repository.save(entity);
    }
}
