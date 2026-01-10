package com.machidior.configuration_service.mapper;


import com.machidior.configuration_service.dtos.request.policy.ProductEligibilityRequest;
import com.machidior.configuration_service.dtos.response.policy.ProductEligibilityResponse;
import com.machidior.configuration_service.enums.ClientType;
import com.machidior.configuration_service.exceptions.InvalidEnumException;
import com.machidior.configuration_service.product.LoanProductVersion;
import com.machidior.configuration_service.product.policy.ProductEligibility;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductEligibilityMapper {

    public ProductEligibility toEntity(ProductEligibilityRequest request, LoanProductVersion productVersion) {
        return ProductEligibility.builder()
                .productVersion(productVersion)
                .maxApplicantAge(request.getMaxApplicantAge())
                .minApplicantAge(request.getMinApplicantAge())
                .minMonthlyIncome(request.getMinMonthlyIncome())
                .minBusinessAgeMonths(request.getMinBusinessAgeMonths())
                .allowedClientTypes(parseClientType(request.getAllowedClientTypes()))
                .minEmploymentDurationMonths(request.getMinEmploymentDurationMonths())
                .allowedRegions(request.getAllowedRegions())
                .allowExistingBorrowers(request.getAllowExistingBorrowers())
                .allowFirstTimeBorrowers(request.getAllowFirstTimeBorrowers())
                .build();
    }

    public ProductEligibilityResponse toResponse(ProductEligibility eligibility) {
        return ProductEligibilityResponse.builder()
                .id(eligibility.getId())
                .minApplicantAge(eligibility.getMinApplicantAge())
                .maxApplicantAge(eligibility.getMaxApplicantAge())
                .minMonthlyIncome(eligibility.getMinMonthlyIncome())
                .minBusinessAgeMonths(eligibility.getMinBusinessAgeMonths())
                .allowedClientTypes(eligibility.getAllowedClientTypes())
                .minEmploymentDurationMonths(eligibility.getMinEmploymentDurationMonths())
                .allowedRegions(eligibility.getAllowedRegions())
                .allowExistingBorrowers(eligibility.getAllowExistingBorrowers())
                .allowFirstTimeBorrowers(eligibility.getAllowFirstTimeBorrowers())
                .build();
    }

    private List<ClientType> parseClientType(List<String> clientTypes) {
        if (clientTypes == null || clientTypes.isEmpty()) {
            return List.of(); // or Collections.emptyList()
        }

        List<ClientType> parsedClientTypes = new ArrayList<>();

        for (String type : clientTypes) {
            try {
                parsedClientTypes.add(
                        ClientType.valueOf(type.trim().toUpperCase())
                );
            } catch (IllegalArgumentException ex) {
                throw new InvalidEnumException("Invalid client type: " + type);
            }
        }

        return parsedClientTypes;
    }

}
