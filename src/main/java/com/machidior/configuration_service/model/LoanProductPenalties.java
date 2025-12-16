package com.machidior.configuration_service.model;

import com.machidior.configuration_service.enums.PenaltyCalculationType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanProductPenalties {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private BigDecimal latePenaltyRate;
    private Integer gracePeriodDays;
    @Enumerated(EnumType.STRING)
    private PenaltyCalculationType calculationType;
}
