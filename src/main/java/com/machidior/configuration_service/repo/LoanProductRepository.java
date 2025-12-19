package com.machidior.configuration_service.repo;

import com.machidior.configuration_service.enums.LoanProductType;
import com.machidior.configuration_service.model.LoanProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanProductRepository extends JpaRepository<LoanProduct, Long> {
    boolean existsByProductType(LoanProductType productType);
    Optional<LoanProduct> findByProductType(LoanProductType productType);
}
