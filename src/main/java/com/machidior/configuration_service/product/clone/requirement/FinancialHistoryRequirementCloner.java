package com.machidior.configuration_service.product.clone.requirement;

import com.machidior.configuration_service.product.clone.AbstractProductConfigCloner;
import com.machidior.configuration_service.product.requirement.FinancialHistoryRequirement;
import com.machidior.configuration_service.product.LoanProductVersion;
import com.machidior.configuration_service.repository.FinancialHistoryRequirementRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FinancialHistoryRequirementCloner extends AbstractProductConfigCloner<FinancialHistoryRequirement> {

    private final FinancialHistoryRequirementRepository repository;

    public FinancialHistoryRequirementCloner(FinancialHistoryRequirementRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<FinancialHistoryRequirement> findByVersion(LoanProductVersion sourceVersion) {
        return repository.findAllByProductVersion(sourceVersion);
    }

    @Override
    public FinancialHistoryRequirement clone(FinancialHistoryRequirement source, LoanProductVersion targetVersion) {
        FinancialHistoryRequirement copy = new FinancialHistoryRequirement();
        copy.setProductVersion(targetVersion);
        copy.setType(source.getType());
        copy.setEnabled(source.getEnabled());
        copy.setMandatory(source.getMandatory());
        copy.setBankStatementRequired(source.getBankStatementRequired());
        copy.setCreditHistoryCheckRequired(source.getCreditHistoryCheckRequired());
        copy.setMobileMoneyStatementRequired(source.getMobileMoneyStatementRequired());
        copy.setBankStatementMonths(source.getBankStatementMonths());
        copy.setMobileMoneyStatementMonths(source.getMobileMoneyStatementMonths());
        copy.setMinCreditScore(source.getMinCreditScore());
        return copy;
    }

    @Override
    public void save(FinancialHistoryRequirement entity) {
        repository.save(entity);
    }
}
