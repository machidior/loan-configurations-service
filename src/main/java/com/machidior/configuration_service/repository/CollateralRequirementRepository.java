package com.machidior.configuration_service.repository;

import com.machidior.configuration_service.product.requirement.CollateralRequirement;
import com.machidior.configuration_service.product.LoanProductVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollateralRequirementRepository extends JpaRepository<CollateralRequirement, Long> {
    List<CollateralRequirement> findByProductVersion(LoanProductVersion productVersion);

    List<CollateralRequirement> findAllByProductVersion(LoanProductVersion sourceVersion);

    boolean existsByProductVersion(LoanProductVersion version);
}

