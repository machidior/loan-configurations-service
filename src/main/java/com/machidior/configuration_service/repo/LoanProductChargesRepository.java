package com.machidior.configuration_service.repo;

import com.machidior.configuration_service.model.LoanProduct;
import com.machidior.configuration_service.model.LoanProductCharges;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanProductChargesRepository extends JpaRepository<LoanProductCharges, Long> {

    Optional<LoanProductCharges> findByLoanProduct(LoanProduct loanProduct);
}
