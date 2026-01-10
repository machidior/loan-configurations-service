package com.machidior.configuration_service.mapper;

import com.machidior.configuration_service.dtos.request.policy.DefaultPolicyRequest;
import com.machidior.configuration_service.dtos.response.policy.DefaultPolicyResponse;
import com.machidior.configuration_service.product.policy.DefaultPolicy;
import com.machidior.configuration_service.product.LoanProductVersion;
import org.springframework.stereotype.Component;

@Component
public class DefaultPolicyMapper {

    public DefaultPolicy toEntity(DefaultPolicyRequest request, LoanProductVersion productVersion) {
        return DefaultPolicy.builder()
                .productVersion(productVersion)
                .daysOverdueToDefault(request.getDaysOverdueToDefault())
                .freezeInterestAccrual(request.getFreezeInterestAccrual())
                .freezePenaltyAccrual(request.getFreezePenaltyAccrual())
                .blockFurtherDisbursement(request.getBlockFurtherDisbursement())
                .markAsNonPerforming(request.getMarkAsNonPerforming())
                .maxAllowedOverdueDays(request.getMaxAllowedOverdueDays())
                .blockIfAnyDefaultedLoan(request.getBlockIfAnyDefaultedLoan())
                .build();
    }

    public DefaultPolicyResponse toResponse(DefaultPolicy policy) {
        return DefaultPolicyResponse.builder()
                .id(policy.getId())
                .daysOverdueToDefault(policy.getDaysOverdueToDefault())
                .freezeInterestAccrual(policy.getFreezeInterestAccrual())
                .freezePenaltyAccrual(policy.getFreezePenaltyAccrual())
                .blockFurtherDisbursement(policy.getBlockFurtherDisbursement())
                .markAsNonPerforming(policy.getMarkAsNonPerforming())
                .maxAllowedOverdueDays(policy.getMaxAllowedOverdueDays())
                .blockIfAnyDefaultedLoan(policy.getBlockIfAnyDefaultedLoan())
                .build();
    }
}
