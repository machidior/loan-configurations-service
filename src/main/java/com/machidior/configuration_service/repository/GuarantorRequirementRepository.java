package com.machidior.configuration_service.repository;

import com.machidior.configuration_service.product.requirement.GuarantorRequirement;
import com.machidior.configuration_service.product.LoanProductVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuarantorRequirementRepository extends JpaRepository<GuarantorRequirement, Long> {
    List<GuarantorRequirement> findByProductVersion(LoanProductVersion productVersion);

    List<GuarantorRequirement> findAllByProductVersion(LoanProductVersion sourceVersion);

    boolean existsByProductVersion(LoanProductVersion version);
}

