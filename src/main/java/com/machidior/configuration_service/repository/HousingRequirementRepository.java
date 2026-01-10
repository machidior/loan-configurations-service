package com.machidior.configuration_service.repository;

import com.machidior.configuration_service.product.requirement.HousingRequirement;
import com.machidior.configuration_service.product.LoanProductVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HousingRequirementRepository extends JpaRepository<HousingRequirement, Long> {
    List<HousingRequirement> findByProductVersion(LoanProductVersion productVersion);

    List<HousingRequirement> findAllByProductVersion(LoanProductVersion sourceVersion);

    boolean existsByProductVersion(LoanProductVersion version);
}

