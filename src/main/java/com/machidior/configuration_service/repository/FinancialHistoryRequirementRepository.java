package com.machidior.configuration_service.repository;

import com.machidior.configuration_service.product.requirement.FinancialHistoryRequirement;
import com.machidior.configuration_service.product.LoanProductVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinancialHistoryRequirementRepository extends JpaRepository<FinancialHistoryRequirement, Long> {
    List<FinancialHistoryRequirement> findByProductVersion(LoanProductVersion productVersion);

    List<FinancialHistoryRequirement> findAllByProductVersion(LoanProductVersion sourceVersion);

    boolean existsByProductVersion(LoanProductVersion version);
}

