package com.machidior.configuration_service.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanProductVersionResponse {

    private Long id;

    private Long productId;

    private Integer version;

    private Boolean locked;
    private Boolean active;

    private String description;

    private LocalDateTime effectiveFrom;

    private LocalDateTime createdAt;
}
