package com.machidior.configuration_service.repo;

import com.machidior.configuration_service.model.LoanProductPenalties;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanProductPenaltiesRepository extends JpaRepository<LoanProductPenalties, Long> {
    Optional<LoanProductPenalties> findByProductId(Long productId);
}
