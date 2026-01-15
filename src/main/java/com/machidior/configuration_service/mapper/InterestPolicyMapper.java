package com.machidior.configuration_service.mapper;

import com.machidior.configuration_service.dtos.request.policy.InterestPolicyRequest;
import com.machidior.configuration_service.dtos.response.policy.InterestPolicyResponse;
import com.machidior.configuration_service.enums.DayCountConversion;
import com.machidior.configuration_service.enums.InterestAccrualMethod;
import com.machidior.configuration_service.enums.InterestType;
import com.machidior.configuration_service.exceptions.InvalidEnumException;
import com.machidior.configuration_service.product.policy.InterestPolicy;
import com.machidior.configuration_service.product.LoanProductVersion;
import org.springframework.stereotype.Component;

@Component
public class InterestPolicyMapper {

    public InterestPolicy toEntity(InterestPolicyRequest request, LoanProductVersion productVersion) {
        return InterestPolicy.builder()
                .productVersion(productVersion)
                .interestType(parseInterestType(request.getInterestType()))
                .monthlyInterestRate(request.getMonthlyInterestRate())
                .minInterestRate(request.getMinInterestRate())
                .maxInterestRate(request.getMaxInterestRate())
                .allowedRateOverride(request.getAllowedRateOverride())
                .accrualMethod(parseInterestAccrualMethod(request.getAccrualMethod()))
                .dayCountConversion(parseDayCountConversion(request.getDayCountConversion()))
                .accrueDuringGracePeriod(request.getAccrueDuringGracePeriod())
                .capitalizedInterest(request.getCapitalizedInterest())
                .build();
    }

    public InterestPolicyResponse toResponse(InterestPolicy policy) {
        return InterestPolicyResponse.builder()
                .id(policy.getId())
                .interestType(policy.getInterestType())
                .monthlyInterestRate(policy.getMonthlyInterestRate())
                .minInterestRate(policy.getMinInterestRate())
                .maxInterestRate(policy.getMaxInterestRate())
                .allowedRateOverride(policy.getAllowedRateOverride())
                .accrualMethod(policy.getAccrualMethod())
                .dayCountConversion(policy.getDayCountConversion())
                .accrueDuringGracePeriod(policy.getAccrueDuringGracePeriod())
                .capitalizedInterest(policy.getCapitalizedInterest())
                .build();
    }

    private InterestType parseInterestType(String interestType) {
        try {
            return interestType != null ? InterestType.valueOf(interestType.toUpperCase()):null;
        } catch (InvalidEnumException e) {
            throw new InvalidEnumException("Invalid Installment Status provided: " +interestType);
        }
    }

    private InterestAccrualMethod parseInterestAccrualMethod(String accrualMethod) {
        try {
            return accrualMethod != null ? InterestAccrualMethod.valueOf(accrualMethod.toUpperCase()):null;
        } catch (InvalidEnumException e) {
            throw new InvalidEnumException("Invalid Interest Accrual method provided: " +accrualMethod);
        }
    }

    private DayCountConversion parseDayCountConversion( String dayCountConversion) {
        try {
            return dayCountConversion != null ? DayCountConversion.valueOf(dayCountConversion.toUpperCase()):null;
        } catch (InvalidEnumException e) {
            throw new InvalidEnumException("Invalid day count conversion provided: " +dayCountConversion);
        }
    }

}
