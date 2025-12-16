package com.machidior.configuration_service.repo;

import com.machidior.configuration_service.model.LoanProductTerms;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanProductTermsRepository extends JpaRepository<LoanProductTerms, Long> {
    Optional<LoanProductTerms> findByProductId(Long productId);
}
