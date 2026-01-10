package com.machidior.configuration_service.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.machidior.configuration_service.product.LoanProductVersion;
import com.machidior.configuration_service.product.requirement.AgricultureRequirement;

@Repository
public interface AgricultureRequirementRepository extends JpaRepository<AgricultureRequirement, Long> {
    List<AgricultureRequirement> findByProductVersion(LoanProductVersion productVersion);

    List<AgricultureRequirement> findAllByProductVersion(LoanProductVersion sourceVersion);

    boolean existsByProductVersion(LoanProductVersion version);
}

