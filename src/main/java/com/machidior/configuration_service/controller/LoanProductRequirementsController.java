package com.machidior.configuration_service.controller;

import com.machidior.configuration_service.dtos.LoanProductRequirementsRequest;
import com.machidior.configuration_service.dtos.LoanProductRequirementsUpdateRequest;
import com.machidior.configuration_service.enums.LoanProductType;
import com.machidior.configuration_service.model.LoanProductRequirements;
import com.machidior.configuration_service.service.LoanProductRequirementsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/loan-product-requirements")
@RequiredArgsConstructor
public class LoanProductRequirementsController {

    private final LoanProductRequirementsService service;

    @PostMapping("/create")
    public ResponseEntity<LoanProductRequirements> createProductRequirements(
            @RequestBody @Valid LoanProductRequirementsRequest request) {
        return ResponseEntity.ok(service.createProductRequirements(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanProductRequirements> getLoanProductRequirements(
            @PathVariable Long id) {
        return ResponseEntity.ok(service.getLoanProductRequirements(id));
    }

    @GetMapping("/product-type/{productType}")
    public ResponseEntity<LoanProductRequirements> getLoanProductRequirementsByProductType(
            @PathVariable LoanProductType productType) {
        return ResponseEntity.ok(service.getLoanProductRequirementsByProductType(productType));
    }

    @GetMapping("/product-id/{productId}")
    public ResponseEntity<LoanProductRequirements> getLoanProductRequirementsByProductId(
            @PathVariable Long productId) {
        return ResponseEntity.ok(service.getLoanProductRequirementsByProductId(productId));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<LoanProductRequirements> updateLoanProductRequirements(
            @PathVariable Long id,
            @RequestBody @Valid LoanProductRequirementsUpdateRequest request) {
        return ResponseEntity.ok(service.updateLoanProductRequirements(id,request));
    }
}
