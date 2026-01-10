package com.machidior.configuration_service.repository;

import com.machidior.configuration_service.product.requirement.AssetRequirement;
import com.machidior.configuration_service.product.LoanProductVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRequirementRepository extends JpaRepository<AssetRequirement, Long> {
    List<AssetRequirement> findByProductVersion(LoanProductVersion productVersion);

    List<AssetRequirement> findAllByProductVersion(LoanProductVersion sourceVersion);

    boolean existsByProductVersion(LoanProductVersion version);
}

