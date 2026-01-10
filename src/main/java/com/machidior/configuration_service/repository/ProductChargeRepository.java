package com.machidior.configuration_service.repository;

import com.machidior.configuration_service.product.charge.ProductCharge;
import com.machidior.configuration_service.product.LoanProductVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductChargeRepository extends JpaRepository<ProductCharge, Long> {
    List<ProductCharge> findByProductVersion(LoanProductVersion productVersion);

    List<ProductCharge> findAllByProductVersion(LoanProductVersion sourceVersion);

    void deleteByProductVersion(LoanProductVersion version);

    List<ProductCharge> findByProductVersionAndMandatory(LoanProductVersion version, boolean b);
}

