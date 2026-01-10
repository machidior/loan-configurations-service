package com.machidior.configuration_service.product.clone.requirement;

import com.machidior.configuration_service.product.clone.AbstractProductConfigCloner;
import com.machidior.configuration_service.product.requirement.HousingRequirement;
import com.machidior.configuration_service.product.LoanProductVersion;
import com.machidior.configuration_service.repository.HousingRequirementRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HousingRequirementCloner extends AbstractProductConfigCloner<HousingRequirement> {

    private final HousingRequirementRepository repository;

    public HousingRequirementCloner(HousingRequirementRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<HousingRequirement> findByVersion(LoanProductVersion sourceVersion) {
        return repository.findAllByProductVersion(sourceVersion);
    }

    @Override
    public HousingRequirement clone(HousingRequirement source, LoanProductVersion targetVersion) {
        HousingRequirement copy = new HousingRequirement();
        copy.setProductVersion(targetVersion);
        copy.setType(source.getType());
        copy.setEnabled(source.getEnabled());
        copy.setMandatory(source.getMandatory());
        copy.setOwnershipProofRequired(source.getOwnershipProofRequired());
        copy.setBuildingPlanRequired(source.getBuildingPlanRequired());
        copy.setSiteInspectionRequired(source.getSiteInspectionRequired());
        copy.setValuationRequired(source.getValuationRequired());
        copy.setMaxLoanToValueRatio(source.getMaxLoanToValueRatio());
        return copy;
    }

    @Override
    public void save(HousingRequirement entity) {
        repository.save(entity);
    }
}

