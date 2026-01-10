package com.machidior.configuration_service.dtos.request;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateProductVersionRequest {
    private String productId;
    private String description;
}
