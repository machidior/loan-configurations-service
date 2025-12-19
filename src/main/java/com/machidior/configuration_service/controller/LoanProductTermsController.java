package com.machidior.configuration_service.controller;

import com.machidior.configuration_service.dtos.LoanProductTermsRequest;
import com.machidior.configuration_service.enums.LoanProductType;
import com.machidior.configuration_service.model.LoanProductTerms;
import com.machidior.configuration_service.service.LoanProductTermsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/loan-product-terms")
@RequiredArgsConstructor
public class LoanProductTermsController {

    private final LoanProductTermsService service;

    @PostMapping
    public ResponseEntity<LoanProductTerms> createTerms(
            @RequestBody @Valid LoanProductTermsRequest request) {
        return ResponseEntity.ok(service.createLoanProductTerms(request));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<LoanProductTerms> updateTerms(
            @PathVariable Long id,
            @RequestBody @Valid LoanProductTermsRequest request) {
        return ResponseEntity.ok(service.updateTerms(id, request));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<LoanProductTerms> getTermsByProductType(
            @PathVariable LoanProductType type) {
        return ResponseEntity.ok(service.getTermsByProductType(type));
    }

    @GetMapping
    public ResponseEntity<List<LoanProductTerms>> getAllTerms() {
        return ResponseEntity.ok(service.getAllTerms());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTerms(
            @PathVariable Long id) {
        service.deleteTerms(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/range")
    public ResponseEntity<List<LoanProductTerms>> getByRange(
            @RequestParam(required = false) BigDecimal min,
            @RequestParam(required = false) BigDecimal max) {
        return ResponseEntity.ok(service.findByAmountRange(min, max));
    }
}