package com.machidior.configuration_service.product.clone.requirement;

import com.machidior.configuration_service.product.clone.AbstractProductConfigCloner;
import com.machidior.configuration_service.product.requirement.DigitalConsentRequirement;
import com.machidior.configuration_service.product.LoanProductVersion;
import com.machidior.configuration_service.repository.DigitalConsentRequirementRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DigitalConsentRequirementCloner
        extends AbstractProductConfigCloner<DigitalConsentRequirement> {

    private final DigitalConsentRequirementRepository repository;

    public DigitalConsentRequirementCloner(DigitalConsentRequirementRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DigitalConsentRequirement> findByVersion(LoanProductVersion sourceVersion) {
        return repository.findAllByProductVersion(sourceVersion);
    }

    @Override
    public DigitalConsentRequirement clone(DigitalConsentRequirement source, LoanProductVersion targetVersion) {
        DigitalConsentRequirement copy = new DigitalConsentRequirement();
        copy.setProductVersion(targetVersion);
        copy.setType(source.getType());
        copy.setEnabled(source.getEnabled());
        copy.setMandatory(source.getMandatory());
        copy.setMobileNumberOwnershipRequired(source.getMobileNumberOwnershipRequired());
        copy.setDigitalCreditScore(source.getDigitalCreditScore());
        copy.setDataConsentRequired(source.getDataConsentRequired());
        return copy;
    }

    @Override
    public void save(DigitalConsentRequirement entity) {
        repository.save(entity);
    }
}
