package com.machidior.configuration_service.dtos.request.policy;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PoliciesRequest {
    private DefaultPolicyRequest defaultPolicy;
    private ProductEligibilityRequest eligibilityPolicy;
    private InterestPolicyRequest interestPolicy;
    private PenaltyPolicyRequest penaltyPolicy;
    private ProductTermsRequest termsPolicy;
}
