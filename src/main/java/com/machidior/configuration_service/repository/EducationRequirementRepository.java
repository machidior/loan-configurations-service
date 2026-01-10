package com.machidior.configuration_service.repository;

import com.machidior.configuration_service.product.requirement.EducationRequirement;
import com.machidior.configuration_service.product.LoanProductVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationRequirementRepository extends JpaRepository<EducationRequirement, Long> {
    List<EducationRequirement> findByProductVersion(LoanProductVersion productVersion);

    List<EducationRequirement> findAllByProductVersion(LoanProductVersion sourceVersion);

    boolean existsByProductVersion(LoanProductVersion version);
}

