package com.machidior.configuration_service.mapper;

import com.machidior.configuration_service.dtos.LoanProductTermsRequest;
import com.machidior.configuration_service.model.LoanProduct;
import com.machidior.configuration_service.model.LoanProductTerms;
import org.springframework.stereotype.Component;

@Component
public class LoanProductTermsMapper {

    public LoanProductTerms toEntity(LoanProductTermsRequest request, LoanProduct loanProduct){
        return LoanProductTerms.builder()
                .loanProduct(loanProduct)
                .minAmount(request.getMinAmount())
                .maxAmount(request.getMaxAmount())
                .maximumTermMonths(request.getMaximumTermMonths())
                .monthlyLoanFeeRate(request.getMonthlyLoanFeeRate())
                .loanFeeRatePer2Months(request.getLoanFeeRatePer2Months())
                .monthlyInterestRate(request.getMonthlyInterestRate())
                .minGroupMembers(request.getMinGroupMembers())
                .maxGroupMembers(request.getMaxGroupMembers())
                .extraRules(request.getExtraRules())
                .build();
    }
}
