package com.machidior.configuration_service.dtos.request;

import com.machidior.configuration_service.dtos.request.charges.ProductChargeRequest;
import com.machidior.configuration_service.dtos.request.policy.PoliciesRequest;
import com.machidior.configuration_service.dtos.request.requirement.RequirementsRequests;

import java.util.List;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConfigurationsRequest {
    private PoliciesRequest policies;

    private List<ProductChargeRequest> charges;

    private RequirementsRequests requirements;
}
