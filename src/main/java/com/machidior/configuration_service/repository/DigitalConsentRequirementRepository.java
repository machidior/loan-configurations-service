package com.machidior.configuration_service.repository;

import com.machidior.configuration_service.product.requirement.DigitalConsentRequirement;
import com.machidior.configuration_service.product.LoanProductVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DigitalConsentRequirementRepository extends JpaRepository<DigitalConsentRequirement, Long> {
    List<DigitalConsentRequirement> findByProductVersion(LoanProductVersion productVersion);

    List<DigitalConsentRequirement> findAllByProductVersion(LoanProductVersion sourceVersion);

    boolean existsByProductVersion(LoanProductVersion version);
}

