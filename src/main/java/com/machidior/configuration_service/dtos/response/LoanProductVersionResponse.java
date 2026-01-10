package com.machidior.configuration_service.dtos.response;

import com.machidior.configuration_service.enums.VersionStatus;
import lombok.*;

import java.time.LocalDate;
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
    private VersionStatus status;

    private String description;

    private LocalDate effectiveFrom;

    private LocalDateTime createdAt;

    private ConfigurationsResponse configurations;
}
