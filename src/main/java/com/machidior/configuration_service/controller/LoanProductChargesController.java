package com.machidior.configuration_service.controller;

import com.machidior.configuration_service.dtos.LoanProductChargesRequest;
import com.machidior.configuration_service.enums.LoanProductType;
import com.machidior.configuration_service.model.LoanProductCharges;
import com.machidior.configuration_service.service.LoanProductChargesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loan-product-charges")
@RequiredArgsConstructor
public class LoanProductChargesController {

    private final LoanProductChargesService service;

    @PostMapping
    public ResponseEntity<LoanProductCharges> createLoanCharges(
            @RequestBody @Valid LoanProductChargesRequest request) {
        return ResponseEntity.ok(service.createLoanCharges(request));
    }

    @GetMapping("/product-id/{productId}")
    public ResponseEntity<LoanProductCharges> getLoanProductChargesByProductId(
            @PathVariable Long productId) {
        return ResponseEntity.ok(service.getLoanProductChargesByProductId(productId));
    }

    @GetMapping("/product-type/{productType}")
    public ResponseEntity<LoanProductCharges> getLoanProductChargesByProductType(
            @PathVariable LoanProductType productType){
        return ResponseEntity.ok(service.getLoanProductChargesByProductType(productType));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<LoanProductCharges> updateProductCharges(
            @PathVariable Long id,
            @RequestBody @Valid LoanProductChargesRequest request){
        return ResponseEntity.ok(service.updateProductCharges(id, request));
    }

    @GetMapping
    public ResponseEntity<List<LoanProductCharges>> getAll() {
        return ResponseEntity.ok(service.getAllCharges());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCharges(
            @PathVariable Long id) {
        service.deleteCharges(id);
        return ResponseEntity.noContent().build();
    }
}
