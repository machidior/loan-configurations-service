package com.machidior.configuration_service.product.clone.requirement;

import com.machidior.configuration_service.product.clone.AbstractProductConfigCloner;
import com.machidior.configuration_service.product.requirement.EmploymentRequirement;
import com.machidior.configuration_service.product.LoanProductVersion;
import com.machidior.configuration_service.repository.EmploymentRequirementRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmploymentRequirementCloner extends AbstractProductConfigCloner<EmploymentRequirement> {

    private final EmploymentRequirementRepository repository;

    public EmploymentRequirementCloner(EmploymentRequirementRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<EmploymentRequirement> findByVersion(LoanProductVersion sourceVersion) {
        return repository.findAllByProductVersion(sourceVersion);
    }

    @Override
    public EmploymentRequirement clone(EmploymentRequirement source, LoanProductVersion targetVersion) {
        EmploymentRequirement copy = new EmploymentRequirement();
        copy.setProductVersion(targetVersion);
        copy.setType(source.getType());
        copy.setEnabled(source.getEnabled());
        copy.setMandatory(source.getMandatory());
        copy.setJobContractRequired(source.getJobContractRequired());
        copy.setPaySlipRequired(source.getPaySlipRequired());
        copy.setPayrollDeductionRequired(source.getPayrollDeductionRequired());
        copy.setBankStatementRequired(source.getBankStatementRequired());
        copy.setMinMonthsEmployed(source.getMinMonthsEmployed());
        copy.setMinNetSalary(source.getMinNetSalary());
        return copy;
    }

    @Override
    public void save(EmploymentRequirement entity) {
        repository.save(entity);
    }
}
