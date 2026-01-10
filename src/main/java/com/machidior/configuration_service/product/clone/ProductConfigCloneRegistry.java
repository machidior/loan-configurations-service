package com.machidior.configuration_service.product.clone;

import com.machidior.configuration_service.product.LoanProductVersion;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductConfigCloneRegistry {

    private final List<ProductConfigCloner<?>> cloners;

    public ProductConfigCloneRegistry(
            List<ProductConfigCloner<?>> cloners) {
        this.cloners = cloners;
    }

    /**
     * Clones all registered product configurations
     * from source version to target version.
     */
    @Transactional
    public void cloneAll(
            LoanProductVersion sourceVersion,
            LoanProductVersion targetVersion) {

        for (ProductConfigCloner<?> cloner : cloners) {
            @SuppressWarnings("unchecked")
            ProductConfigCloner<Object> typedCloner =
                    (ProductConfigCloner<Object>) cloner;

            typedCloner.cloneAll(sourceVersion, targetVersion);
        }
    }
}

