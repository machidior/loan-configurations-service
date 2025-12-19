package com.machidior.configuration_service.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PenaltyPolicyUpdateRequest {

    @NotNull(message = "Penalty rate is required.")
    private BigDecimal latePenaltyRate;
    @Min(0)
    @NotNull(message = "Grace period days in null")
    private Integer gracePeriodDays;
    @NotNull(message = "Penalty method type must be provided.")
    private String calculationType;
}
