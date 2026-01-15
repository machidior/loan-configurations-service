package com.machidior.configuration_service.controller;

import com.machidior.configuration_service.dtos.response.charges.ProductChargeResponse;
import com.machidior.configuration_service.dtos.response.policy.PoliciesResponse;
import com.machidior.configuration_service.dtos.response.requirement.RequirementsResponse;
import com.machidior.configuration_service.service.ProductConfigurationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/internal/product-configurations")
@RequiredArgsConstructor
public class ProductConfigurationInternalController {

    private final ProductConfigurationService service;

    @GetMapping("/requirements/{productVersionId}")
    public RequirementsResponse getVersionRequirements( @PathVariable Long productVersionId) {
        return service.getVersionRequirements(productVersionId);
    }

    @GetMapping("/policies/{productVersionId}")
    public PoliciesResponse getVersionPolicies(@PathVariable Long productVersionId) {
        return service.getVersionPolicies(productVersionId);
    }

    @GetMapping("/charges/{productVersionId}")
    public List<ProductChargeResponse> getVersionCharges(@PathVariable Long productVersionId) {
        return service.getOnApplicationCharges(productVersionId);
    }
}
