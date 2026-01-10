package com.machidior.configuration_service.dtos.request.charges;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductChargeRequest {

    private Long productVersionId;

    private String name;

    private String type;

    private String calculationMethod;

    private BigDecimal fixedAmount;

    private BigDecimal percentage;

    private BigDecimal minAmount;
    private BigDecimal maxAmount;

    private String trigger;

    private String payer;

    private Boolean refundable;
    private Boolean mandatory;
    private Boolean allowOverride;

}
