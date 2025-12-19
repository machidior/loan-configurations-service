package com.machidior.configuration_service.controller;

import com.machidior.configuration_service.dtos.LoanProductRequest;
import com.machidior.configuration_service.dtos.LoanProductUpdateRequest;
import com.machidior.configuration_service.enums.LoanProductType;
import com.machidior.configuration_service.model.LoanProduct;
import com.machidior.configuration_service.service.LoanProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loan-product")
@RequiredArgsConstructor
public class LoanProductController {

    private final LoanProductService service;

    @PostMapping("/create")
    public ResponseEntity<LoanProduct> createLoanProduct(
            @RequestBody @Valid LoanProductRequest request){
        return ResponseEntity.ok(service.createLoanProduct(request));
    }

    @GetMapping("/all")
    public ResponseEntity<List<LoanProduct>> getAllLoanProducts(){
        return ResponseEntity.ok(service.getAllLoanProducts());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<LoanProduct> getLoanProductById(
            @PathVariable Long id){
        return ResponseEntity.ok(service.getLoanProductById(id));
    }

    @GetMapping("/product-type/{productType}")
    public ResponseEntity<LoanProduct> getLoanProductByProductType(
            @PathVariable LoanProductType productType){
        return ResponseEntity.ok(service.getLoanProductByProductType(productType));
    }

    @GetMapping("/activate/{id}")
    public ResponseEntity<?> activateLoanProduct(
            @PathVariable Long id){
        service.activateLoanProduct(id);
        return ResponseEntity.ok().body("Loan product activated successfully.");
    }

    @GetMapping("/deactivate/{id}")
    public ResponseEntity<?> deActivateLoanProduct(
            @PathVariable Long id){
        service.deActivateLoanProduct(id);
        return ResponseEntity.ok().body("Loan product deactivated successfully.");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<LoanProduct> updateProductDetails(
            @PathVariable Long id,
            @RequestBody @Valid LoanProductUpdateRequest request){
        return ResponseEntity.ok(service.updateProductDetails(id,request));
    }

}
