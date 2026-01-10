package com.machidior.configuration_service.product.clone.requirement;

import com.machidior.configuration_service.product.clone.AbstractProductConfigCloner;
import com.machidior.configuration_service.product.requirement.AssetRequirement;
import com.machidior.configuration_service.product.LoanProductVersion;
import com.machidior.configuration_service.repository.AssetRequirementRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssetRequirementCloner
        extends AbstractProductConfigCloner<AssetRequirement> {

    private final AssetRequirementRepository repository;

    public AssetRequirementCloner(AssetRequirementRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AssetRequirement> findByVersion(LoanProductVersion sourceVersion) {
        return repository.findAllByProductVersion(sourceVersion);
    }

    @Override
    public AssetRequirement clone(AssetRequirement source, LoanProductVersion targetVersion) {
        AssetRequirement copy = new AssetRequirement();
        copy.setProductVersion(targetVersion);
        copy.setType(source.getType());
        copy.setEnabled(source.getEnabled());
        copy.setMandatory(source.getMandatory());
        copy.setQuotationRequired(source.getQuotationRequired());
        copy.setSupplierVerificationRequired(source.getSupplierVerificationRequired());
        copy.setAssetInsuranceRequired(source.getAssetInsuranceRequired());
        copy.setAssetOwnershipTransferredToLender(source.getAssetOwnershipTransferredToLender());
        return copy;
    }

    @Override
    public void save(AssetRequirement entity) {
        repository.save(entity);
    }
}

