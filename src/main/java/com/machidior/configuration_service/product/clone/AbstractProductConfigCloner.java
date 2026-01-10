package com.machidior.configuration_service.product.clone;

import com.machidior.configuration_service.product.LoanProductVersion;

import java.util.List;

public abstract class AbstractProductConfigCloner<T>
        implements ProductConfigCloner<T> {

    @Override
    public void cloneAll(
            LoanProductVersion sourceVersion,
            LoanProductVersion targetVersion) {

        List<T> configs = findByVersion(sourceVersion);

        if (configs == null || configs.isEmpty()) {
            return;
        }

        for (T config : configs) {
            T cloned = clone(config, targetVersion);
            save(cloned);
        }
    }
}
