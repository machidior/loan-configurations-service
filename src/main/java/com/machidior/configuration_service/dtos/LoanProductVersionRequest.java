package com.machidior.configuration_service.dtos;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanProductVersionRequest {
    private String productId;
    private String description;
}
