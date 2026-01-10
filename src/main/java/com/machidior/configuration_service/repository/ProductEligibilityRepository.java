package com.machidior.configuration_service.repository;

import com.machidior.configuration_service.product.policy.ProductEligibility;
import com.machidior.configuration_service.product.LoanProductVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductEligibilityRepository extends JpaRepository<ProductEligibility, Long> {

    Optional<ProductEligibility> findByProductVersion(LoanProductVersion productVersion);

    List<ProductEligibility> findAllByProductVersion(LoanProductVersion sourceVersion);

    boolean existsByProductVersion(LoanProductVersion version);
}


