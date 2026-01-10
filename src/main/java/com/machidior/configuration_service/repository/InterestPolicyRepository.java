package com.machidior.configuration_service.repository;

import com.machidior.configuration_service.product.policy.InterestPolicy;
import com.machidior.configuration_service.product.LoanProductVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InterestPolicyRepository extends JpaRepository<InterestPolicy, Long> {
    Optional<InterestPolicy> findByProductVersion(LoanProductVersion productVersion);

    List<InterestPolicy> findAllByProductVersion(LoanProductVersion sourceVersion);

    boolean existsByProductVersion(LoanProductVersion version);
}


