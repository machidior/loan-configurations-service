package com.machidior.configuration_service.dtos;

import com.machidior.configuration_service.enums.LoanProductType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanProductRequest {

    @NotNull(message = "Please provide product type.")
    private LoanProductType productType;
    @NotBlank(message = "Name of the loan product must not be blank.")
    private String name;
}
