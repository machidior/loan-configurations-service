package com.machidior.configuration_service.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoanProductUpdateRequest {
    @NotNull(message = "Product name must be not null")
    @NotBlank(message = "Product must not be blank")
    private String name;
    private String description;
}
