package com.machidior.configuration_service.product.clone.requirement;

import com.machidior.configuration_service.product.clone.AbstractProductConfigCloner;
import com.machidior.configuration_service.product.requirement.GuarantorRequirement;
import com.machidior.configuration_service.product.LoanProductVersion;
import com.machidior.configuration_service.repository.GuarantorRequirementRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GuarantorRequirementCloner extends AbstractProductConfigCloner<GuarantorRequirement> {

    private final GuarantorRequirementRepository repository;

    public GuarantorRequirementCloner(GuarantorRequirementRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<GuarantorRequirement> findByVersion(LoanProductVersion sourceVersion) {
        return repository.findAllByProductVersion(sourceVersion);
    }

    @Override
    public GuarantorRequirement clone(GuarantorRequirement source, LoanProductVersion targetVersion) {
        GuarantorRequirement copy = new GuarantorRequirement();
        copy.setProductVersion(targetVersion);
        copy.setType(source.getType());
        copy.setEnabled(source.getEnabled());
        copy.setMandatory(source.getMandatory());
        copy.setMinGuarantors(source.getMinGuarantors());
        copy.setMaxGuarantors(source.getMaxGuarantors());
        copy.setGuarantorIncomeProofRequired(source.getGuarantorIncomeProofRequired());
        copy.setGuarantorEmploymentRequired(source.getGuarantorEmploymentRequired());
        copy.setGuarantorRelationRequired(source.getGuarantorRelationRequired());
        copy.setMinGuarantorIncome(source.getMinGuarantorIncome());
        return copy;
    }

    @Override
    public void save(GuarantorRequirement entity) {
        repository.save(entity);
    }
}
