package com.machidior.configuration_service.product.clone.requirement;

import com.machidior.configuration_service.product.clone.AbstractProductConfigCloner;
import com.machidior.configuration_service.product.requirement.CollateralRequirement;
import com.machidior.configuration_service.product.LoanProductVersion;
import com.machidior.configuration_service.repository.CollateralRequirementRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CollateralRequirementCloner
        extends AbstractProductConfigCloner<CollateralRequirement> {

    private final CollateralRequirementRepository repository;

    public CollateralRequirementCloner(CollateralRequirementRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CollateralRequirement> findByVersion(LoanProductVersion sourceVersion) {
        return repository.findAllByProductVersion(sourceVersion);
    }

    @Override
    public CollateralRequirement clone(CollateralRequirement source, LoanProductVersion targetVersion) {
        CollateralRequirement copy = new CollateralRequirement();
        copy.setProductVersion(targetVersion);
        copy.setType(source.getType());
        copy.setEnabled(source.getEnabled());
        copy.setMandatory(source.getMandatory());
        copy.setAllowedTypes(source.getAllowedTypes() == null ? null : List.copyOf(source.getAllowedTypes()));
        copy.setMinCount(source.getMinCount());
        copy.setMaxCount(source.getMaxCount());
        copy.setDescriptionRequired(source.getDescriptionRequired());
        copy.setOwnershipProofRequired(source.getOwnershipProofRequired());
        copy.setInsuranceRequired(source.getInsuranceRequired());
        copy.setValuationRequired(source.getValuationRequired());
        copy.setPhotoRequired(source.getPhotoRequired());
        copy.setMinLoanAmountToValueRatio(source.getMinLoanAmountToValueRatio());
        return copy;
    }

    @Override
    public void save(CollateralRequirement entity) {
        repository.save(entity);
    }
}
