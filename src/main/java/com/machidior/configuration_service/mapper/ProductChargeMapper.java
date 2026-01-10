package com.machidior.configuration_service.mapper;

import com.machidior.configuration_service.dtos.request.charges.ProductChargeRequest;
import com.machidior.configuration_service.dtos.response.charges.ProductChargeResponse;
import com.machidior.configuration_service.enums.ChargeCalculationMethod;
import com.machidior.configuration_service.enums.ChargePayer;
import com.machidior.configuration_service.enums.ChargeTrigger;
import com.machidior.configuration_service.enums.ChargeType;
import com.machidior.configuration_service.exceptions.InvalidEnumException;
import com.machidior.configuration_service.product.LoanProductVersion;
import com.machidior.configuration_service.product.charge.ProductCharge;
import org.springframework.stereotype.Component;

@Component
public class ProductChargeMapper {

    public ProductCharge toEntity(ProductChargeRequest request, LoanProductVersion productVersion) {
        return ProductCharge.builder()
                .productVersion(productVersion)
                .name(request.getName())
                .type(parseChargeType(request.getType()))
                .calculationMethod(parseChargeCalculationMethod(request.getCalculationMethod()))
                .fixedAmount(request.getFixedAmount())
                .percentage(request.getPercentage())
                .minAmount(request.getMinAmount())
                .maxAmount(request.getMaxAmount())
                .trigger(parseChargeTrigger(request.getTrigger()))
                .payer(parseChargePayer(request.getPayer()))
                .refundable(request.getRefundable())
                .mandatory(request.getMandatory())
                .allowOverride(request.getAllowOverride())
                .build();
    }

    public ProductChargeResponse toResponse(ProductCharge charge) {
        return ProductChargeResponse.builder()
                .id(charge.getId())
                .name(charge.getName())
                .type(charge.getType())
                .calculationMethod(charge.getCalculationMethod())
                .fixedAmount(charge.getFixedAmount())
                .percentage(charge.getPercentage())
                .minAmount(charge.getMinAmount())
                .maxAmount(charge.getMaxAmount())
                .trigger(charge.getTrigger())
                .payer(charge.getPayer())
                .refundable(charge.getRefundable())
                .mandatory(charge.getMandatory())
                .allowOverride(charge.getAllowOverride())
                .build();
    }

    private ChargeType parseChargeType(String type) {
        try {
            return type != null? ChargeType.valueOf(type.toUpperCase()):null;
        } catch (InvalidEnumException e) {
            throw new InvalidEnumException("Invalid charge type provided: " + type);
        }
    }

    private ChargeCalculationMethod parseChargeCalculationMethod(String method) {
        try {
            return method != null? ChargeCalculationMethod.valueOf(method.toUpperCase()):null;
        } catch (InvalidEnumException e) {
            throw new InvalidEnumException("Invalid charge calculation method provided: " +method);
        }
    }

    private ChargeTrigger parseChargeTrigger(String trigger) {
        try {
            return trigger != null? ChargeTrigger.valueOf(trigger.toUpperCase()):null;
        } catch (InvalidEnumException e) {
            throw new InvalidEnumException("Invalid charge trigger provided: " + trigger);
        }
    }

    private ChargePayer parseChargePayer(String payer) {
        try {
            return payer != null? ChargePayer.valueOf(payer.toUpperCase()):null;
        } catch (InvalidEnumException e) {
            throw new InvalidEnumException("Invalid charge payer provided: " +payer);
        }
    }
}
