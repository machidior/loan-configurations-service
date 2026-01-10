package com.machidior.configuration_service.repository;

import com.machidior.configuration_service.product.LoanProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanProductRepository extends JpaRepository<LoanProduct, Long> {
//    Optional<LoanProduct> findByExternalId(String externalId);

    boolean existsByCode(String code);
}

