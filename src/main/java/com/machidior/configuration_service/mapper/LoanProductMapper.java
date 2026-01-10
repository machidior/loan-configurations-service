package com.machidior.configuration_service.mapper;

import com.machidior.configuration_service.dtos.request.LoanProductRequest;
import com.machidior.configuration_service.dtos.response.LoanProductResponse;
import com.machidior.configuration_service.enums.LoanProductCategory;
import com.machidior.configuration_service.enums.LoanProductType;
import com.machidior.configuration_service.exceptions.InvalidEnumException;
import com.machidior.configuration_service.product.LoanProduct;
import org.springframework.stereotype.Component;

@Component
public class LoanProductMapper {

    public LoanProduct toEntity(LoanProductRequest request) {
        return LoanProduct.builder()
                .productType(parseLoanProductType(request.getProductType()))
                .name(request.getName())
                .code(request.getCode())
                .description(request.getDescription())
                .category(parseLoanProductCategory(request.getCategory()))
                .build();
    }

    public LoanProductResponse toResponse(LoanProduct product) {
        return LoanProductResponse.builder()
                .id(product.getId())
                .productType(product.getProductType())
                .name(product.getName())
                .code(product.getCode())
                .description(product.getDescription())
                .category(product.getCategory())
                .active(product.getActive())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    private LoanProductType parseLoanProductType(String productType) {
        try {
            return productType != null ? LoanProductType.valueOf(productType.toUpperCase()): null;
        } catch (InvalidEnumException e) {
            throw new InvalidEnumException("Invalid Loan Product Type Provided: " +productType);
        }
    }

    private LoanProductCategory parseLoanProductCategory(String category) {
        try {
            return category != null ? LoanProductCategory.valueOf(category.toUpperCase()): null;
        } catch (InvalidEnumException e) {
            throw new InvalidEnumException("Invalid Loan product category provided: " +category);
        }
    }
}
