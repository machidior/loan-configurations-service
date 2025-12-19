package com.machidior.configuration_service.mapper;

import com.machidior.configuration_service.dtos.PenaltyPolicyRequest;
import com.machidior.configuration_service.enums.PenaltyCalculationType;
import com.machidior.configuration_service.exceptions.InvalidEnumException;
import com.machidior.configuration_service.model.LoanProduct;
import com.machidior.configuration_service.model.PenaltyPolicy;
import org.springframework.stereotype.Component;

@Component
public class PenaltyPolicyMapper {

    public PenaltyPolicy toEntity(PenaltyPolicyRequest request, LoanProduct loanProduct) {

        return PenaltyPolicy.builder()
                .loanProduct(loanProduct)
                .latePenaltyRate(request.getLatePenaltyRate())
                .gracePeriodDays(request.getGracePeriodDays())
                .calculationType(parseCalculationType(request.getCalculationType()))
                .build();
    }

    private PenaltyCalculationType parseCalculationType(String calculationType){

        try {
            return calculationType != null? PenaltyCalculationType.valueOf(calculationType.toUpperCase()):null;
        } catch (InvalidEnumException e) {
            throw new InvalidEnumException("Invalid penalty calculation type: " + calculationType);
        }

    }
}
