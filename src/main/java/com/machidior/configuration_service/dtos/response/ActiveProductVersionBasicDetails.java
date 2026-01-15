package com.machidior.configuration_service.dtos.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActiveProductVersionBasicDetails {

    private Long productId;
    private Long productVersionId;
    private String productName;
    private String productCode;
}
