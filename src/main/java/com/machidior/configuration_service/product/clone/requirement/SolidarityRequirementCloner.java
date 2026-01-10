package com.machidior.configuration_service.product.clone.requirement;

import com.machidior.configuration_service.product.clone.AbstractProductConfigCloner;
import com.machidior.configuration_service.product.requirement.SolidarityRequirement;
import com.machidior.configuration_service.product.LoanProductVersion;
import com.machidior.configuration_service.repository.SolidarityRequirementRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SolidarityRequirementCloner extends AbstractProductConfigCloner<SolidarityRequirement> {

    private final SolidarityRequirementRepository repository;

    public SolidarityRequirementCloner(SolidarityRequirementRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SolidarityRequirement> findByVersion(LoanProductVersion sourceVersion) {
        return repository.findAllByProductVersion(sourceVersion);
    }

    @Override
    public SolidarityRequirement clone(SolidarityRequirement source, LoanProductVersion targetVersion) {
        SolidarityRequirement copy = new SolidarityRequirement();
        copy.setProductVersion(targetVersion);
        copy.setType(source.getType());
        copy.setEnabled(source.getEnabled());
        copy.setMandatory(source.getMandatory());
        copy.setGroupRegistrationRequired(source.getGroupRegistrationRequired());
        copy.setGroupGuaranteeRequired(source.getGroupGuaranteeRequired());
        copy.setGroupMeetingRecordsRequired(source.getGroupMeetingRecordsRequired());
        copy.setMinGroupMembers(source.getMinGroupMembers());
        copy.setMaxGroupMembers(source.getMaxGroupMembers());
        return copy;
    }

    @Override
    public void save(SolidarityRequirement entity) {
        repository.save(entity);
    }
}
