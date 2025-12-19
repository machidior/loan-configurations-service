package com.machidior.configuration_service.controller;

import com.machidior.configuration_service.dtos.PenaltyPolicyRequest;
import com.machidior.configuration_service.dtos.PenaltyPolicyUpdateRequest;
import com.machidior.configuration_service.enums.LoanProductType;
import com.machidior.configuration_service.model.PenaltyPolicy;
import com.machidior.configuration_service.service.PenaltyPolicyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/penalty-policy")
@RequiredArgsConstructor
public class PenaltyPolicyController {

    private final PenaltyPolicyService service;

    @PostMapping("/create")
    public ResponseEntity<PenaltyPolicy> createPenaltyPolicy(
            @RequestBody @Valid PenaltyPolicyRequest request){
        return ResponseEntity.ok(service.createPenaltyPolicy(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PenaltyPolicy> getPenaltyPolicy(
            @PathVariable Long id){
        return ResponseEntity.ok(service.getPenaltyPolicy(id));
    }

    @GetMapping("/product-type/{productType}")
    public ResponseEntity<PenaltyPolicy> getPenaltyPolicyByProductType(
            @PathVariable LoanProductType productType) {
        return ResponseEntity.ok(service.getPenaltyPolicyByProductType(productType));
    }

    @GetMapping("/product-id/{productId}")
    public ResponseEntity<PenaltyPolicy> getPenaltyPolicyByProductId(
            @PathVariable Long productId) {
        return ResponseEntity.ok(service.getPenaltyPolicyByProductId(productId));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PenaltyPolicy> updatePenaltyPolicy(
            @PathVariable Long id,
            @RequestBody @Valid PenaltyPolicyUpdateRequest request){
        return ResponseEntity.ok(service.updatePenaltyPolicy(id,request));
    }

}
