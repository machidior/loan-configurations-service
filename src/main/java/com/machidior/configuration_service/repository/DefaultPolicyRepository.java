package com.machidior.configuration_service.repository;

import com.machidior.configuration_service.product.policy.DefaultPolicy;
import com.machidior.configuration_service.product.LoanProductVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DefaultPolicyRepository extends JpaRepository<DefaultPolicy, Long> {
    Optional<DefaultPolicy> findByProductVersion(LoanProductVersion productVersion);

    List<DefaultPolicy> findAllByProductVersion(LoanProductVersion sourceVersion);

    boolean existsByProductVersion(LoanProductVersion version);
}


