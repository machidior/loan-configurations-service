package com.machidior.configuration_service.repository;

import com.machidior.configuration_service.product.policy.PenaltyPolicy;
import com.machidior.configuration_service.product.LoanProductVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PenaltyPolicyRepository extends JpaRepository<PenaltyPolicy, Long> {
    Optional<PenaltyPolicy> findByProductVersion(LoanProductVersion productVersion);

    List<PenaltyPolicy> findAllByProductVersion(LoanProductVersion sourceVersion);
    Optional<PenaltyPolicy> findByProductVersionId(Long versionId);

    boolean existsByProductVersion(LoanProductVersion version);
}


