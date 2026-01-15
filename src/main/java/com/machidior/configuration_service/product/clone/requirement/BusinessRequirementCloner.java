package com.machidior.configuration_service.product.clone.requirement;

import com.machidior.configuration_service.product.clone.AbstractProductConfigCloner;
import com.machidior.configuration_service.product.requirement.BusinessRequirement;
import com.machidior.configuration_service.product.LoanProductVersion;
import com.machidior.configuration_service.repository.BusinessRequirementRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BusinessRequirementCloner
        extends AbstractProductConfigCloner<BusinessRequirement> {

    private final BusinessRequirementRepository repository;

    public BusinessRequirementCloner(BusinessRequirementRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<BusinessRequirement> findByVersion(LoanProductVersion sourceVersion) {
        return repository.findAllByProductVersion(sourceVersion);
    }

    @Override
    public BusinessRequirement clone(BusinessRequirement source, LoanProductVersion targetVersion) {
        BusinessRequirement copy = new BusinessRequirement();
        copy.setProductVersion(targetVersion);
        copy.setType(source.getType());
        copy.setEnabled(source.getEnabled());
        copy.setMandatory(source.getMandatory());
        copy.setRegistrationRequired(source.getRegistrationRequired());
        copy.setBusinessLicenseRequired(source.getBusinessLicenseRequired());
        copy.setCashFlowStatementRequired(source.getCashFlowStatementRequired());
        copy.setTinCertificateRequired(source.getTinCertificateRequired());
        copy.setTinNumberRequired(source.getTinNumberRequired());
        copy.setInsuranceComprehensiveCoverRequired(source.getInsuranceComprehensiveCoverRequired());
        copy.setMinYearsInOperation(source.getMinYearsInOperation());
        copy.setMinAverageMonthlyTurnOver(source.getMinAverageMonthlyTurnOver());
        return copy;
    }

    @Override
    public void save(BusinessRequirement entity) {
        repository.save(entity);
    }
}

