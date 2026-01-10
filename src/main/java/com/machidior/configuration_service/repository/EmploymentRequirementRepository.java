package com.machidior.configuration_service.repository;

import com.machidior.configuration_service.product.requirement.EmploymentRequirement;
import com.machidior.configuration_service.product.LoanProductVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmploymentRequirementRepository extends JpaRepository<EmploymentRequirement, Long> {
    List<EmploymentRequirement> findByProductVersion(LoanProductVersion productVersion);

    List<EmploymentRequirement> findAllByProductVersion(LoanProductVersion sourceVersion);

    boolean existsByProductVersion(LoanProductVersion version);
}

