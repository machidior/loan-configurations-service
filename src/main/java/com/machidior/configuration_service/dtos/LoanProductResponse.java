package com.machidior.configuration_service.dtos;

import com.machidior.configuration_service.enums.LoanProductCategory;
import com.machidior.configuration_service.enums.LoanProductType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanProductResponse {

    private Long id;
    private LoanProductType productType;

    private String name;
    private String code;
    private String description;
    private LoanProductCategory category;
    private Boolean active;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
