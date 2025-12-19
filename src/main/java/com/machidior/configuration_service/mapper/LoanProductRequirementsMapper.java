package com.machidior.configuration_service.mapper;

import com.machidior.configuration_service.dtos.LoanProductRequirementsRequest;
import com.machidior.configuration_service.enums.CollateralType;
import com.machidior.configuration_service.exceptions.InvalidEnumException;
import com.machidior.configuration_service.model.LoanProduct;
import com.machidior.configuration_service.model.LoanProductRequirements;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoanProductRequirementsMapper {

    public LoanProductRequirements toEntity(LoanProductRequirementsRequest request, LoanProduct loanProduct){
        return LoanProductRequirements.builder()
                .loanProduct(loanProduct)
                .requiresGuarantor(request.getRequiresGuarantor())
                .minGuarantors(request.getMinGuarantors())
                .requiresCollateral(request.getRequiresCollateral())
                .allowedCollateralTypes(parseCollateralTypes(request.getAllowedCollateralTypes()))
                .build();
    }

    private List<CollateralType> parseCollateralTypes(List<String> types){
        try {
            List<CollateralType> collateralTypes = null;
            for (String type: types){
                collateralTypes.add(CollateralType.valueOf(type));
            }
            return collateralTypes;
        } catch ( InvalidEnumException e){
            throw new InvalidEnumException("Invalid Collateral type.");
        }
    }
}
