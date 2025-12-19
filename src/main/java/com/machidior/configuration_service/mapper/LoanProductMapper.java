package com.machidior.configuration_service.mapper;

import com.machidior.configuration_service.dtos.LoanProductRequest;
import com.machidior.configuration_service.enums.LoanProductType;
import com.machidior.configuration_service.exceptions.InvalidEnumException;
import com.machidior.configuration_service.model.LoanProduct;
import org.springframework.stereotype.Component;

@Component
public class LoanProductMapper {

    public LoanProduct toEntity(LoanProductRequest request){
        return LoanProduct.builder()
                .productType(parseProductType(request.getProductType()))
                .name(request.getName())
                .description(request.getDescription())
                .active(true)
                .build();
    }

    private LoanProductType parseProductType(String productType){
        try {
            return productType != null? LoanProductType.valueOf(productType):null;
        } catch (InvalidEnumException e){
            throw new InvalidEnumException("Invalid Loan product type: " + productType);
        }
    }
}
