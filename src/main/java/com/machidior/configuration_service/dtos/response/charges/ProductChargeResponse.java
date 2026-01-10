package com.machidior.configuration_service.dtos.response.charges;

import com.machidior.configuration_service.enums.ChargeCalculationMethod;
import com.machidior.configuration_service.enums.ChargePayer;
import com.machidior.configuration_service.enums.ChargeTrigger;
import com.machidior.configuration_service.enums.ChargeType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductChargeResponse {

    private Long id;

    private String name;

    private ChargeType type;

    private ChargeCalculationMethod calculationMethod;

    private BigDecimal fixedAmount;

    private BigDecimal percentage;

    private BigDecimal minAmount;
    private BigDecimal maxAmount;

    private ChargeTrigger trigger;

    private ChargePayer payer;

    private Boolean refundable;
    private Boolean mandatory;
    private Boolean allowOverride;
}
