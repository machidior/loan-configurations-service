package com.machidior.configuration_service.product.clone.requirement;

import com.machidior.configuration_service.product.clone.AbstractProductConfigCloner;
import com.machidior.configuration_service.product.requirement.EducationRequirement;
import com.machidior.configuration_service.product.LoanProductVersion;
import com.machidior.configuration_service.repository.EducationRequirementRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EducationRequirementCloner extends AbstractProductConfigCloner<EducationRequirement> {

    private final EducationRequirementRepository repository;

    public EducationRequirementCloner(EducationRequirementRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<EducationRequirement> findByVersion(LoanProductVersion sourceVersion) {
        return repository.findAllByProductVersion(sourceVersion);
    }

    @Override
    public EducationRequirement clone(EducationRequirement source, LoanProductVersion targetVersion) {
        EducationRequirement copy = new EducationRequirement();
        copy.setProductVersion(targetVersion);
        copy.setType(source.getType());
        copy.setEnabled(source.getEnabled());
        copy.setMandatory(source.getMandatory());
        copy.setAdmissionLetterRequired(source.getAdmissionLetterRequired());
        copy.setFeeStructureRequired(source.getFeeStructureRequired());
        copy.setSponsorRequired(source.getSponsorRequired());
        copy.setGuarantorRequired(source.getGuarantorRequired());
        copy.setRelatedEducationCertificateRequired(source.getRelatedEducationCertificateRequired());
        return copy;
    }

    @Override
    public void save(EducationRequirement entity) {
        repository.save(entity);
    }
}
