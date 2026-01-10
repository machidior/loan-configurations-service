package com.machidior.configuration_service.service;

import com.machidior.configuration_service.dtos.request.charges.ProductChargeRequest;
import com.machidior.configuration_service.dtos.request.policy.PoliciesRequest;
import com.machidior.configuration_service.dtos.request.requirement.RequirementsRequests;
import com.machidior.configuration_service.dtos.response.ConfigurationsResponse;
import com.machidior.configuration_service.dtos.response.charges.ProductChargeResponse;
import com.machidior.configuration_service.dtos.response.policy.PoliciesResponse;
import com.machidior.configuration_service.dtos.response.requirement.RequirementsResponse;

import java.util.List;

public interface ProductConfigurationService {
    PoliciesResponse updatePolicies(Long versionId, PoliciesRequest policies);

    List<ProductChargeResponse> updateCharges(Long versionId, List<ProductChargeRequest> chargeDTOs);

    RequirementsResponse updateRequirements(Long versionId, RequirementsRequests requirements);

    ConfigurationsResponse getFullConfiguration(Long versionId);
}
