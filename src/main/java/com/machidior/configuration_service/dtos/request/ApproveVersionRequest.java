package com.machidior.configuration_service.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApproveVersionRequest {
    private LocalDate effectiveFrom;

    private LocalDate effectiveTo;

    @NotBlank
    private String approvedBy;

    private String description;
}
