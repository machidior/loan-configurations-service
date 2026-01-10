package com.machidior.configuration_service.product.clone.requirement;

import com.machidior.configuration_service.product.clone.AbstractProductConfigCloner;
import com.machidior.configuration_service.product.requirement.AgricultureRequirement;
import com.machidior.configuration_service.product.LoanProductVersion;
import com.machidior.configuration_service.repository.AgricultureRequirementRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AgricultureRequirementCloner
        extends AbstractProductConfigCloner<AgricultureRequirement> {

    private final AgricultureRequirementRepository repository;

    public AgricultureRequirementCloner(AgricultureRequirementRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AgricultureRequirement> findByVersion(LoanProductVersion sourceVersion) {
        return repository.findAllByProductVersion(sourceVersion);
    }

    @Override
    public AgricultureRequirement clone(AgricultureRequirement source, LoanProductVersion targetVersion) {
        AgricultureRequirement copy = new AgricultureRequirement();
        copy.setProductVersion(targetVersion);
        copy.setType(source.getType());
        copy.setEnabled(source.getEnabled());
        copy.setMandatory(source.getMandatory());
        copy.setFarmDetailsRequired(source.getFarmDetailsRequired());
        copy.setProductionCycleRequired(source.getProductionCycleRequired());
        copy.setOffTakerAgreement(source.getOffTakerAgreement());
        copy.setFarmInspectionRequired(source.getFarmInspectionRequired());
        copy.setMinFarmSize(source.getMinFarmSize());
        return copy;
    }

    @Override
    public void save(AgricultureRequirement entity) {
        repository.save(entity);
    }
}
