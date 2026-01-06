package com.machidior.configuration_service.mapper;

import com.machidior.configuration_service.dtos.PenaltyPolicyRequest;
import com.machidior.configuration_service.dtos.PenaltyPolicyResponse;
import com.machidior.configuration_service.enums.PenaltyCalculationMethod;
import com.machidior.configuration_service.enums.PenaltyFrequency;
import com.machidior.configuration_service.enums.PenaltyType;
import com.machidior.configuration_service.exceptions.InvalidEnumException;
import com.machidior.configuration_service.model.LoanProductVersion;
import com.machidior.configuration_service.model.PenaltyPolicy;
import org.springframework.stereotype.Component;

@Component
public class PenaltyPolicyMapper {

    public PenaltyPolicy toEntity(PenaltyPolicyRequest request, LoanProductVersion productVersion) {
        return PenaltyPolicy.builder()
                .productVersion(productVersion)
                .graceDaysAfterDueDate(request.getGraceDaysAfterDueDate())
                .penaltyType(parsePenaltyType(request.getPenaltyType()))
                .calculationMethod(parseCalculationMethod(request.getCalculationMethod()))
                .fixedAmount(request.getFixedAmount())
                .percentage(request.getPercentage())
                .maxPenaltyAmount(request.getMaxPenaltyAmount())
                .frequency(parsepenaltyFrequency(request.getFrequency()))
                .compoundPenalty(request.getCompoundPenalty())
                .maxPenaltyDays(request.getMaxPenaltyDays())
                .build();
    }

    public PenaltyPolicyResponse toResponse(PenaltyPolicy policy) {
        return PenaltyPolicyResponse.builder()
                .id(policy.getId())
                .graceDaysAfterDueDate(policy.getGraceDaysAfterDueDate())
                .penaltyType(policy.getPenaltyType())
                .calculationMethod(policy.getCalculationMethod())
                .fixedAmount(policy.getFixedAmount())
                .percentage(policy.getPercentage())
                .maxPenaltyAmount(policy.getMaxPenaltyAmount())
                .frequency(policy.getFrequency())
                .compoundPenalty(policy.getCompoundPenalty())
                .maxPenaltyDays(policy.getMaxPenaltyDays())
                .build();
    }

    private PenaltyType parsePenaltyType(String type) {
        try {
            return type != null ? PenaltyType.valueOf(type):null;
        } catch (InvalidEnumException e) {
            throw new InvalidEnumException("Invalid penalty type provided: " +type);
        }
    }

    private PenaltyCalculationMethod parseCalculationMethod(String calculationMethod) {
        try {
            return calculationMethod != null ? PenaltyCalculationMethod.valueOf(calculationMethod): null;
        } catch (InvalidEnumException e) {
            throw new InvalidEnumException("Invalid penalty calculation method provided: " +calculationMethod);
        }
    }

    private PenaltyFrequency parsepenaltyFrequency(String frequency) {
        try {
            return frequency != null ? PenaltyFrequency.valueOf(frequency):null;
        } catch (InvalidEnumException e) {
            throw new InvalidEnumException("Invalid penalty frequency provided: " + frequency);
        }
    }

}
