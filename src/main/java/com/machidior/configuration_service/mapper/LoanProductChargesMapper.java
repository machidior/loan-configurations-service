package com.machidior.configuration_service.mapper;

import com.machidior.configuration_service.dtos.LoanProductChargesRequest;
import com.machidior.configuration_service.model.LoanProduct;
import com.machidior.configuration_service.model.LoanProductCharges;
import org.springframework.stereotype.Component;

@Component
public class LoanProductChargesMapper {

    public LoanProductCharges toEntity(LoanProductChargesRequest request, LoanProduct loanProduct){
        return LoanProductCharges.builder()
                .loanProduct(loanProduct)
                .firstApplicationFee(request.getFirstApplicationFee())
                .subsequentApplicationFee(request.getSubsequentApplicationFee())
                .loanInsurancePercent(request.getLoanInsurancePercent())
                .groupInsurancePercent(request.getGroupInsurancePercent())
                .extraRules(request.getExtraRules())
                .build();
    }
}
