package com.machidior.configuration_service.dtos.response.policy;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PoliciesResponse {
    private DefaultPolicyResponse defaultPolicy;
    private ProductEligibilityResponse eligibilityPolicy;
    private InterestPolicyResponse interestPolicy;
    private PenaltyPolicyResponse penaltyPolicy;
    private ProductTermsResponse termsPolicy;
}
