package com.machidior.configuration_service.service;

import com.machidior.configuration_service.enums.LoanProductType;
import com.machidior.configuration_service.model.LoanProductCharges;
import com.machidior.configuration_service.repo.LoanProductChargesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanProductChargesService {

    private final LoanProductChargesRepository repository;

    public LoanProductChargesService(LoanProductChargesRepository repository) {
        this.repository = repository;
    }

    public LoanProductCharges createOrUpdateCharges(LoanProductCharges charges) {
        return repository.save(charges);
    }

    public LoanProductCharges getChargesByProductType(LoanProductType type) {
        return repository.findByProductType(type)
                .orElseThrow(() -> new RuntimeException("Charges not defined for product type: " + type));
    }

    public List<LoanProductCharges> getAllCharges() {
        return repository.findAll();
    }

    public void deleteCharges(Long id) {
        repository.deleteById(id);
    }
}