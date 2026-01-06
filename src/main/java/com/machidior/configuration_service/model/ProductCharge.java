package com.machidior.configuration_service.model;

import com.machidior.configuration_service.enums.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCharge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private LoanProductVersion productVersion;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ChargeType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ChargeCalculationMethod calculationMethod;

    private BigDecimal fixedAmount;

    private BigDecimal percentage;

    private BigDecimal minAmount;
    private BigDecimal maxAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ChargeTrigger trigger;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ChargePayer payer;

    private Boolean refundable = false;
    private Boolean mandatory = true;
    private Boolean allowOverride = false;

}
