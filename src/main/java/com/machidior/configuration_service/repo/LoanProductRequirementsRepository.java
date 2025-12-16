package com.machidior.configuration_service.repo;

import com.machidior.configuration_service.model.LoanProductRequirements;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanProductRequirementsRepository extends JpaRepository<LoanProductRequirements, Long> {
    Optional<LoanProductRequirements> findByProductId(Long productId);
}
