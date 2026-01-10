package com.machidior.configuration_service.repository;

import com.machidior.configuration_service.product.policy.ProductTerms;
import com.machidior.configuration_service.product.LoanProductVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductTermsRepository extends JpaRepository<ProductTerms, Long> {
    Optional<ProductTerms> findByProductVersion(LoanProductVersion productVersion);

    List<ProductTerms> findAllByProductVersion(LoanProductVersion sourceVersion);

    boolean existsByProductVersion(LoanProductVersion version);
}


