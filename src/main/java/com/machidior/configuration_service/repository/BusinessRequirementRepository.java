package com.machidior.configuration_service.repository;

import com.machidior.configuration_service.product.requirement.BusinessRequirement;
import com.machidior.configuration_service.product.LoanProductVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessRequirementRepository extends JpaRepository<BusinessRequirement, Long> {
    List<BusinessRequirement> findByProductVersion(LoanProductVersion productVersion);

    List<BusinessRequirement> findAllByProductVersion(LoanProductVersion sourceVersion);

    boolean existsByProductVersion(LoanProductVersion version);
}

