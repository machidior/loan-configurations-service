package com.machidior.configuration_service.repo;

import com.machidior.configuration_service.model.LoanProduct;
import com.machidior.configuration_service.model.PenaltyPolicy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PenaltyPolicyRepository extends JpaRepository<PenaltyPolicy, Long> {
    Optional<PenaltyPolicy> findByLoanProduct(LoanProduct loanProduct);
}
