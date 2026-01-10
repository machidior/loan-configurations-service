package com.machidior.configuration_service.product.clone;

import com.machidior.configuration_service.product.LoanProductVersion;

import java.util.List;

public interface ProductConfigCloner<T> {

    /**
     * Finds all configuration entities of this type
     * belonging to the given product version.
     */
    List<T> findByVersion(LoanProductVersion sourceVersion);

    /**
     * Creates a deep copy of the given configuration entity
     * and attaches it to the target product version.
     */
    T clone(T source, LoanProductVersion targetVersion);

    /**
     * Persists the cloned configuration entity.
     */
    void save(T entity);

    /**
     * Convenience method to clone all configs
     * from source version to target version.
     */
    default void cloneAll(
            LoanProductVersion sourceVersion,
            LoanProductVersion targetVersion) {

        findByVersion(sourceVersion)
                .forEach(cfg ->
                        save(clone(cfg, targetVersion)));
    }
}
