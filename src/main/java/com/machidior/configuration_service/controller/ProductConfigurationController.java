package com.machidior.configuration_service.controller;


import com.machidior.configuration_service.dtos.request.charges.ProductChargeRequest;
import com.machidior.configuration_service.dtos.request.policy.PoliciesRequest;
import com.machidior.configuration_service.dtos.request.requirement.RequirementsRequests;
import com.machidior.configuration_service.dtos.response.charges.ProductChargeResponse;
import com.machidior.configuration_service.dtos.response.policy.PoliciesResponse;
import com.machidior.configuration_service.dtos.response.requirement.RequirementsResponse;
import com.machidior.configuration_service.service.ProductConfigurationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product-configurations")
@RequiredArgsConstructor
public class ProductConfigurationController {

    private final ProductConfigurationService productConfigurationService;

    @PostMapping("/update-policies/{productVersionId}")
    public ResponseEntity<PoliciesResponse> updatePolicies(@PathVariable Long productVersionId,
                                                           @RequestBody PoliciesRequest request) {
        return ResponseEntity.ok(productConfigurationService.updatePolicies(productVersionId, request));
    }

    @PostMapping("/update-charges/{productVersionId}")
    public ResponseEntity<List<ProductChargeResponse>> updateCharges(@PathVariable Long productVersionId,
                                                                     @RequestBody List<ProductChargeRequest> request) {
        return ResponseEntity.ok(productConfigurationService.updateCharges(productVersionId, request));
    }

    @PostMapping("/update-requirements/{productVersionId}")
    public ResponseEntity<RequirementsResponse> updateRequirements(@PathVariable Long productVersionId,
                                                                   @RequestBody RequirementsRequests request) {
        return ResponseEntity.ok(productConfigurationService.updateRequirements(productVersionId, request));
    }
}
