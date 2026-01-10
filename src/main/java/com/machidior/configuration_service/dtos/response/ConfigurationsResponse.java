package com.machidior.configuration_service.dtos.response;

import com.machidior.configuration_service.dtos.response.charges.ProductChargeResponse;
import com.machidior.configuration_service.dtos.response.policy.PoliciesResponse;
import com.machidior.configuration_service.dtos.response.requirement.RequirementsResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConfigurationsResponse {
    private PoliciesResponse policies;

    private List<ProductChargeResponse> charges;

    private RequirementsResponse requirements;
}
