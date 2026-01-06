package com.machidior.configuration_service.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanProductRequest {

    private String productType;

    private String name;
    private String code;
    private String description;
    private String category;

}
