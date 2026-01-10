package com.machidior.configuration_service.product.clone.charge;

import com.machidior.configuration_service.product.charge.ProductCharge;
import com.machidior.configuration_service.product.clone.AbstractProductConfigCloner;
import com.machidior.configuration_service.product.LoanProductVersion;
import com.machidior.configuration_service.repository.ProductChargeRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductChargeCloner
        extends AbstractProductConfigCloner<ProductCharge> {

    private final ProductChargeRepository repository;

    public ProductChargeCloner(ProductChargeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ProductCharge> findByVersion(LoanProductVersion sourceVersion) {
        return repository.findAllByProductVersion(sourceVersion);
    }

    @Override
    public ProductCharge clone(ProductCharge source, LoanProductVersion targetVersion) {
        ProductCharge copy = new ProductCharge();

        copy.setProductVersion(targetVersion);
        copy.setName(source.getName());
        copy.setType(source.getType());
        copy.setCalculationMethod(source.getCalculationMethod());
        copy.setFixedAmount(source.getFixedAmount());
        copy.setPercentage(source.getPercentage());
        copy.setMinAmount(source.getMinAmount());
        copy.setMaxAmount(source.getMaxAmount());
        copy.setTrigger(source.getTrigger());
        copy.setPayer(source.getPayer());
        copy.setRefundable(source.getRefundable());
        copy.setMandatory(source.getMandatory());
        copy.setAllowOverride(source.getAllowOverride());

        return copy;
    }

    @Override
    public void save(ProductCharge entity) {
        repository.save(entity);
    }
}

