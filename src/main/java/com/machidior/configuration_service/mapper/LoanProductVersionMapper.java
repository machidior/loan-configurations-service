package com.machidior.configuration_service.mapper;

import com.machidior.configuration_service.dtos.response.LoanProductVersionResponse;
import com.machidior.configuration_service.product.LoanProductVersion;
import org.springframework.stereotype.Component;

@Component
public class LoanProductVersionMapper {

    public LoanProductVersionResponse toResponse(LoanProductVersion productVersion) {
        return LoanProductVersionResponse.builder()
                .id(productVersion.getId())
                .productId(productVersion.getProduct().getId())
                .version(productVersion.getVersion())
                .locked(productVersion.getLocked())
                .active(productVersion.getActive())
                .status(productVersion.getStatus())
                .description(productVersion.getDescription())
//                .effectiveFrom(productVersion.getEffectiveFrom())
                .createdAt(productVersion.getCreatedAt())
                .build();
    }
}
